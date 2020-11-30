package edu.utn.manager;

import edu.utn.entity.User;
import edu.utn.enums.Result;
import edu.utn.helper.EncryptHelper;
import edu.utn.log.LogHelper;
import edu.utn.mail.Mail;
import edu.utn.mapper.UserMapper;
import edu.utn.validator.UserValidator;
import java.util.List;

public class UserManager implements Manager <User> {

    private UserValidator validator;
    private UserMapper mapper;

    public UserManager (UserMapper mapper, UserValidator validator){
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public boolean save (User user) {

        boolean value = false;

        try {
            value = mapper.save(user);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return value;
    }

    @Override
    public boolean update (User user){

        boolean value = false;

        try {
            value = mapper.update(user);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }
        return value;
    }

    @Override
    public boolean delete(User user) {

        boolean value = false;

        try {
            value = mapper.delete(user);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return value;
    }

    public User get(String email) {

        User  user = null;

        try {
            user = mapper.get(email);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return user;
    }

    @Override
    public User get (long id) {

        User  user = null;

        try {
              user =  mapper.get(id);
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return user;
    }

    public List<User> getAllUser () {

        List<User> users = null;

        try {
            users = mapper.getAllUsers();
        }catch (Exception exception){
            LogHelper.createNewErrorLog(exception.getMessage());
        }

        return users;
    }

    /**
     * Se encargar de registrar el usuario
     */
    public boolean signIn (User user) {

        boolean value = validator.isValidUser(user);
        value &= !validator.existsUser(user.getEmail());

        if(value) {
            try {
                user.setPassword(EncryptHelper.encryptPassword(user.getPassword()));
                value = save(user);
            }catch (Exception ex){
                LogHelper.createNewErrorLog(ex.getMessage());
            }
        }

        if(value) {
            LogHelper.createNewDebugLog(Result.SIGN_IN_OK);
            Mail.sendMail(user.getEmail(), Result.SIGN_IN_OK, "Gracias por registrarse!");
        }

        return value;
    }

    /**
     * Este metodo se encarga de loggear el usuario. Tendria que ser un poco mas corto este metodo,
     * lo que hacemos es que al menor valor no valido conrtamos.
     * @return Result
     */
    public Result logIn (String email, String password) {

        Result result = Result.ERR_AUTHENTICATION;
        User user = get(email);

        if(validator.isNull(user)){
            return Result.ERR_USER_DOES_NOT_EXIST;
        }
        if (validator.isLogIn(user)) {
            result = Result.OK;
            result.setUser(user);
            return result;
        }
        if(validator.isLocked(user)){
            return Result.ERR_IS_LOCKED;
        }

        password = EncryptHelper.encryptPassword(password);
        boolean value = user.getPassword().equals(password);


        if(value){
            user.setLogIn(true);
            user.setAttemptLogin(0);
        }
        else {
            user.setAttemptLogin(user.getAttemptLogin() + 1);
            if(!validator.attemptsRemain(user)){
                result = Result.LOCKED_ACCOUNT;
                user.setAttemptLogin(0);
            }
        }

        value &= update(user);
        if(value){
            result = Result.OK;
            result.setUser(user);
            LogHelper.createNewDebugLog(Result.LOG_IN_OK);
            Mail.sendMail(email, result, "Se inicio sesion en su cuenta");
        }
        return result;
    }

    /**
     *Este metodo cierra la sesion
     * param id del usuario
     */
    public boolean logOut(long id){

        User user = get(id);
        user.setLogIn(false);
        boolean value = update(user);
        LogHelper.createNewDebugLog(Result.LOG_OUT_OK);
        return value;
    }

    /**
     * Este metodo cambia la contraseña
     */
    public boolean changePassword(long id, String newPassword){

        User user = get(id);

        boolean value = validator.isValidPassword(newPassword) && !user.getPassword().equals(newPassword);

        if(value){
            LogHelper.createNewDebugLog(Result.CHANGE_PASSWORD);
            user.setPassword(EncryptHelper.encryptPassword(newPassword));
            value &= update(user);
        }
        else {
            LogHelper.createNewErrorLog(Result.CHANGE_PASSWORD_FAIL.getDescription());
        }

        if(value) Mail.sendMail(user.getEmail(), Result.CHANGE_PASSWORD, "Se cambio la contrasenia");

        return value;
    }

    /**
     * Este metodo solicita el desbloqueo de su cuenta
     */
    public boolean requestUnlockedAccount (String email, String endpoint) {

        User user = get(email);

        boolean value = validator.isLocked(user);
        if(value){
            value &= Mail.sendMail(email, Result.UNLOCKED_ACCOUNT, "Ingrese a esta ruta para desbloquear su cuenta " + endpoint);
        }
        return value;
    }

    /**
     * Este metodo desbloquea la cuenta
     */
    public boolean unLockedAccount (String email){

        User user = get(email);
        user.setLocked(false);

        boolean value = update(user);
        if(value){
            LogHelper.createNewDebugLog(Result.UNLOCKED_ACCOUNT_OK);
        }
        else{
            LogHelper.createNewErrorLog(Result.UNLOCKED_ACCOUNT_FAIL.getDescription());
        }
        return value;
    }

    /**
     * Este metodo se solicita cuando se olvida la contrasena
     */
    public boolean forgotPassword (String email) {
        User user = get(email);
        boolean value = !validator.isNull(user);

        if(value){
            Mail.sendMail(email, Result.RESET_PASSWORD, "http://localhost:8080/webapi/resetPassword.html");
        }
        return value;
    }


    public UserValidator getValidator() {
        return validator;
    }

    public void setValidator(UserValidator validator) {
        this.validator = validator;
    }

    public UserMapper getMapper() {
        return mapper;
    }

    public void setMapper(UserMapper mapper) {
        this.mapper = mapper;
    }

}

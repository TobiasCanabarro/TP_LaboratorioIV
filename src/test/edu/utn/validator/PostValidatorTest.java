package test.edu.utn.validator;

import edu.utn.validator.PostValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostValidatorTest {

    @Test
    void isValidLength() {

        String post = "Hola Mundo!";
        PostValidator validator = new PostValidator();
        boolean value = validator.IsValidLength(post);

        assertEquals(true, value);
    }

    @Test
    void isValidLengthFail() {
        String post = "La programación orientada a objetos difiere de la programación estructurada tradicional, en la que los datos y " +
                "los procedimientos están separados y sin relación, ya que lo único que se busca es el procesamiento de unos datos de " +
                "entrada para obtener otros de salida. La programación estructurada anima al programador a pensar sobre todo en términos de p" +
                "rocedimientos o funciones, y en segundo lugar en las estructuras de datos que esos procedimientos manejan. En la programación " +
                "estructurada solo se escriben funciones que procesan datos. Los programadores que emplean POO, en cambio, primero definen objetos " +
                "para luego enviarles mensajes solicitándoles que realicen sus métodos por sí mismos Origen";
        PostValidator validator = new PostValidator();
        boolean value = validator.IsValidLength(post);

        assertEquals(false, value);
    }
}
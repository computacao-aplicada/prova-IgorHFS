import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidadorTest {

    // Testa se CPFs válidos são aceitos pelo método
    @Test
    void deveValidarCPFValido() {
        assertTrue(Validador.validarCPF("529.982.247-25")); // CPF com máscara
        assertTrue(Validador.validarCPF("52998224725"));    // CPF sem máscara
    }

    // Testa se CPFs com sequências repetidas são rejeitados
    @Test
    void deveRejeitarSequenciasRepetidasLimite() {
        assertFalse(Validador.validarCPF("111.111.111-11")); // todos os dígitos iguais
        assertFalse(Validador.validarCPF("22222222222"));    // todos os dígitos iguais sem máscara
        assertFalse(Validador.validarCPF("999.999.999-99")); // limite alto de repetição
    }

    // Testa se CPFs com caracteres inválidos são rejeitados
    @Test
    void deveRejeitarMisturaCaracteresInvalidos() {
        assertFalse(Validador.validarCPF("529.98a.247-25")); // letra no meio
        assertFalse(Validador.validarCPF("529*982*247*25")); // símbolos especiais
    }

    // Testa se CPFs com dígito verificador incorreto são rejeitados
    @Test
    void deveRejeitarDVIncorretoGenerico() {
        assertFalse(Validador.validarCPF("123.456.789-00")); // DV errado
    }
}

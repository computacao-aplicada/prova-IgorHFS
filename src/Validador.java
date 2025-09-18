public class Validador {

    // Método principal que valida um CPF
    public static boolean validarCPF(String cpf) {
        if (cpf == null) return false; // Retorna falso se o CPF for nulo

        // Remove espaços, pontos e traços do CPF
        String limpo = cpf.trim().replaceAll("[\\.\\-\\s]", "");

        // Verifica se tem exatamente 11 dígitos
        if (!limpo.matches("\\d{11}")) return false;

        // Verifica se todos os dígitos são iguais (CPF inválido)
        if (limpo.chars().distinct().count() == 1) return false;

        // Verifica os dígitos verificadores
        return checarDigitos(limpo);
    }

    // Método privado que calcula e checa os dígitos verificadores do CPF
    private static boolean checarDigitos(String cpf) {
        // Converte cada caractere do CPF em um número inteiro
        int[] d = cpf.chars().map(c -> c - '0').toArray();

        // Calcula o primeiro dígito verificador
        int s1 = 0;
        for (int i = 0; i < 9; i++) s1 += d[i] * (10 - i); // soma ponderada
        int r1 = s1 % 11;                                   // resto da divisão por 11
        int dv1 = (r1 < 2) ? 0 : 11 - r1;                   // regra do DV
        if (d[9] != dv1) return false;                      // compara com o CPF

        // Calcula o segundo dígito verificador
        int s2 = 0;
        for (int i = 0; i < 10; i++) s2 += d[i] * (11 - i); // soma ponderada
        int r2 = s2 % 11;                                   // resto da divisão por 11
        int dv2 = (r2 < 2) ? 0 : 11 - r2;                   // regra do DV

        // Retorna true se o segundo dígito coincidir, caso contrário false
        return d[10] == dv2;
    }
}

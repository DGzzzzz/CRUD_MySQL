import java.util.Date;

public class App {
    public static void main(String[] args) {
        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Gabriela");
        contato.setIdade(24);
        contato.setDataCadastro(new Date());

        contatoDao.save(contato);
    }
}

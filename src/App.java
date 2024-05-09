import java.util.Date;

public class App {
    public static void main(String[] args) {
        ContatoDAO contatoDao = new ContatoDAO();

        Contato contato = new Contato();
        contato.setNome("Matheus");
        contato.setIdade(24);
        contato.setDataCadastro(new Date());

        // contatoDao.save(contato);
        
        //atualizar contato
        Contato c1 = new Contato();
        c1.setNome("Matheus Atualizado");
        c1.setIdade(25);
        c1.setDataCadastro(new Date());
        c1.setId(3); //Codigo do cliente que será atualizado no banco de dados

        // contatoDao.update(c1);

        //Deletar contato
        contatoDao.deleteByID(3); //Codigo do cliente que será deletado no banco de dados

        //Visualização dos registros do banco de dados TODOS
        for(Contato c : contatoDao.getContatos()) {
            System.out.println("Nome: " + c.getNome());
            System.out.println("Idade: " + c.getIdade());
            System.out.println("Data de Cadastro: " + c.getDataCadastro());
        }
    }
}

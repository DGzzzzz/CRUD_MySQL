import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ContatoDAO {
    //CRUD - Create(ok), Read, Update, Delete

    public void save(Contato contato) {
        String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES(?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            //Cria uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            //Adiciona os parâmetros para a query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            //Executa a query
            pstm.execute();
            System.out.println("Salvo com Sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //Fechar as conexões
            try {
                if(pstm!=null){
                    pstm.close();
                }
                if(conn!=null){
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    //CRUD - Create(ok), Read(ok), Update(ok), Delete

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

    //UPDATE 
    public void update(Contato contato) {
        String sql = "UPDATE contatos set nome = ?, idade = ?, dataCadastro = ? " + "where id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        //Cria uma conexão com o banco de dados
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            //Adiciona os parâmetros para a query
            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            //Qual contato será atualizado
            pstm.setInt(4, contato.getId());
            //Executa a query
            pstm.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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

    public void deleteByID(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        //Cria uma conexão com o banco de dados
        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            pstm.execute();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
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

    //READ    
    public List<Contato> getContatos() {
        String sql = "SELECT * FROM contatos";

        List<Contato> contatos = new ArrayList<Contato>();

        Connection conn = null;
        PreparedStatement pstm = null;
        //Classe que vai recuperar os dados do banco de dados. ***SELECT***
        ResultSet rset = null;

        try {
            //Cria uma conexão com o banco de dados
            conn = ConnectionFactory.createConnectionToMySQL();
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();
            //Enquanto existir dados no banco de dados, faça
            while(rset.next()){
                Contato contato = new Contato();
                //recuperar o id
                contato.setId(rset.getInt("id"));
                //recuperar o nome
                contato.setNome(rset.getString("nome"));
                //recuperar a idade
                contato.setIdade(rset.getInt("idade"));
                //recuperar a data de cadastro
                contato.setDataCadastro(rset.getDate("dataCadastro"));
                //Adiciono o contato recuperado, a lista de contatos
                contatos.add(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
    } finally {
        //Fechar as conexões
        try {
            if(rset!=null){
                rset.close();
            }
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
    return contatos;
    }
}        
package org.example.DAO;

import org.example.model.ConnectionDatabase;
import org.example.biblioteca.Application;
import org.example.model.LivrosModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivrosDAO {

    public void salvar(LivrosModel livro){
        String sql = "INSERT INTO livros(nome,valor,posicao,autor) VALUES(?,?,?,?)";
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionDatabase.creatConnectionToMySQL();

            pstm = conn.prepareStatement(sql);
            pstm.setString(1,livro.getNome());
            pstm.setFloat(2,livro.getValor());
            pstm.setInt(3,livro.getPosicao());
            pstm.setString(4,livro.getAutor());
            pstm.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try{
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(LivrosModel livro){
        String sql = "UPDATE livros SET nome= ?, valor= ?, posicao= ?, autor=? WHERE codigo = ? ";

        Connection conn = null;
        PreparedStatement pstm = null;
        try{
            conn = ConnectionDatabase.creatConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1,livro.getNome());
            pstm.setFloat(2,livro.getValor());
            pstm.setInt(3,livro.getPosicao());
            pstm.setString(4,livro.getAutor());
            pstm.setInt(5,livro.getCodigo());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }



    public List<LivrosModel> getlivros(){
        String sql= "SELECT * FROM livros";

        List<LivrosModel> livros = new ArrayList<LivrosModel>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rst = null;

        try{
            conn = ConnectionDatabase.creatConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rst = pstm.executeQuery();

            while (rst.next()){
                LivrosModel livro = new LivrosModel();
                //Recuperando codigo
                livro.setCodigo(rst.getInt("Codigo"));
                //Recuperando o nome
                livro.setNome(rst.getString("Nome"));
                // Recuperando o valor
                livro.setValor(rst.getFloat("Valor"));
                // Recuperando a posição
                livro.setPosicao(rst.getInt("Posicao"));

                getlivros().add(livro);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(rst!=null){
                try {
                    rst.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (pstm!=null){
                try {
                    pstm.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return livros;

    }
}

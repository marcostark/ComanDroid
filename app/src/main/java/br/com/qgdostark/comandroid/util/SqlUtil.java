package br.com.qgdostark.comandroid.util;

/**
 * Created by stark on 08/07/17.
 */

public class SqlUtil {

    //---------------------------PRODUTOS-------------------------------------
    public static final String BD_PRODUTOS_NOME_TABLE = "produtos";
    public static final String COLUNA_PRODUTO_ID = "id";
    public static final String COLUNA_PRODUTO_NOME = "desc_produto";
    public static final String COLUNA_PRODUTO_DESCRICAO = "descricao";
    public static final String COLUNA_PRODUTO_VALOR = "valor";
    public static final String COLUNA_PRODUTO_CATEGORIA = "categoria";
    public static final String COLUNA_PRODUTO_CATEGORIA_ID = "categoria_id";

    public static final String SQL_CREATE_TABLE_PRODUTO = "CREATE TABLE " + BD_PRODUTOS_NOME_TABLE + " ( " +
            COLUNA_PRODUTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUNA_PRODUTO_NOME + " TEXT NOT NULL , " +
            COLUNA_PRODUTO_DESCRICAO + " TEXT NOT NULL , " +
            COLUNA_PRODUTO_VALOR + " REAL NOT NULL,  " +
            COLUNA_PRODUTO_CATEGORIA + " TEXT NOT NULL, " +
            COLUNA_PRODUTO_CATEGORIA_ID +" TEXT NOT NULL " +
            "); ";

    public static final String SQL_INSERT_TABLE_PRODUTO = "INSERT INTO " + BD_PRODUTOS_NOME_TABLE + " VALUES ( " +
            "1,'Calabresa', 'Calabresa, queijo, presunto e tomate', 35.50, 'pizzas', 1)";

    //public static final String DROP_PRODUTOS_TABLE = "DROP TABLE IF EXISTS " + BD_PRODUTOS_NOME_TABLE;



    //----------------------------MESAS-----------------------------------------


    public static final String BD_MESA_NOME_TABLE = "mesas";
    public static final String COLUNA_MESA_ID = "id";
    public static final String COLUNA_MESA_NOME = "desc_mesa";
    public static final String COLUNA_MESA_STATUS = "status";

    public static final String SQL_CREATE_TABLE_MESA = "CREATE TABLE " + BD_MESA_NOME_TABLE + " ( " +
            COLUNA_MESA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUNA_MESA_NOME + " TEXT NOT NULL, " +
            COLUNA_MESA_STATUS + " INTEGER " +
            "); ";

    public static final String SQL_INSERT_TABLE_MESA = "INSERT INTO " + BD_MESA_NOME_TABLE + " VALUES ( " +
            "1,'1',0)";

    // ------------------------------PEDIDO------------------------------------------


    public static final String BD_PEDIDOS_NOME_TABLE = "pedidos";
    public static final String  COLUNA_PEDIDO_ID= "id";
    public static final String  COLUNA_PEDIDO_DATAVENDA= "dataDaVenda";
    public static final String  COLUNA_PEDIDO_VALORTOTAL= "valorTotal";
    public static final String  COLUNA_PEDIDO_MESA= "mesa_id";
    public static final String  COLUNA_PEDIDO_ISPRINTER= "status_imprimir";
    public static final String  COLUNA_PEDIDO_ISFATURADO= "status_faturado";

    public static final String SQL_CREATE_TABLE_PEDIDO = "CREATE TABLE " + BD_PEDIDOS_NOME_TABLE + " ( " +
            COLUNA_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUNA_PEDIDO_DATAVENDA + " TEXT NOT NULL , " +
            COLUNA_PEDIDO_VALORTOTAL + " REAL, " +
            COLUNA_PEDIDO_MESA + " INTEGER NOT NULL , " +
            COLUNA_PEDIDO_ISPRINTER + " INTEGER , " +
            COLUNA_PEDIDO_ISFATURADO + " INTEGER , " +
            "FOREIGN KEY("+COLUNA_PEDIDO_MESA +") REFERENCES " + BD_MESA_NOME_TABLE + "("+ COLUNA_MESA_ID+")" +
            ");";





    // ------------------------------PRODUTO_PEDIDO------------------------------------------

    public static final String BD_PRODUTO_PEDIDO_NOME_TABLE = "produtopedido";
    public static final String COLUNA_PRODUTO_PEDIDO_ID = "id";
    public static final String COLUNA_PRODUTO_PEDIDO_NOME = "nome" ;
    public static final String COLUNA_PRODUTO_PEDIDO_SUBTOTAL = "subtotal";
    public static final String COLUNA_PRODUTO_PEDIDO_QUANTIDADE = "quantidade";
    public static final String COLUNA_PRODUTO_PEDIDO_PRODUTO_ID = "produto_id";
    public static final String COLUNA_PRODUTO_PEDIDO_PEDIDO_ID = "pedido_id";


    public static final String SQL_CREATE_TABLE_PRODUTO_PEDIDO = "CREATE TABLE " + BD_PRODUTO_PEDIDO_NOME_TABLE + " ( " +
            COLUNA_PRODUTO_PEDIDO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUNA_PRODUTO_PEDIDO_NOME + " TEXT NOT NULL, " +
            COLUNA_PRODUTO_PEDIDO_SUBTOTAL + " TEXT NOT NULL, " +
            COLUNA_PRODUTO_PEDIDO_QUANTIDADE + " TEXT NOT NULL, " +
            COLUNA_PRODUTO_PEDIDO_PRODUTO_ID + " INTEGER NOT NULL, " +
            COLUNA_PRODUTO_PEDIDO_PEDIDO_ID + " INTEGER NOT NULL, " +
            "FOREIGN KEY("+COLUNA_PRODUTO_PEDIDO_PRODUTO_ID +") REFERENCES " + BD_PRODUTOS_NOME_TABLE + "("+ COLUNA_PRODUTO_ID+")," +
            "FOREIGN KEY("+COLUNA_PRODUTO_PEDIDO_PEDIDO_ID +") REFERENCES " + BD_PEDIDOS_NOME_TABLE + "("+ COLUNA_PEDIDO_ID+")" +
            ");";


    //--------------------------------CATEGORIAS-----------------------------------------------------------
    public static final String BD_CATEGORIA_NOME_TABLE = "categorias";
    public static final String COLUNA_CATEGORIA_ID = "id";
    public static final String COLUNA_CATEGORIA_NOME = "nome";
    public static final String COLUNA_CATEGORIA_DESCRICAO = "descricao";
    public static final String COLUNA_CATEGORIA_THUMB = "thumb";

    public static final String SQL_CREATE_TABLE_CATEGORIA = "CREATE TABLE " + BD_CATEGORIA_NOME_TABLE + " ( " +
            COLUNA_CATEGORIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUNA_CATEGORIA_NOME + " TEXT NOT NULL, " +
            COLUNA_CATEGORIA_DESCRICAO + " TEXT NOT NULL, " +
            COLUNA_CATEGORIA_THUMB + " TEXT NOT NULL " +
            "); ";

    public static final String SQL_INSERT_TABLE_CATEGORIA_PIZZA =
            "INSERT INTO " + BD_CATEGORIA_NOME_TABLE + " VALUES ( " +
            "1,'Pizzas', 'Calabresa, queijo, presunto e tomate','pizzas')";

    public static final String SQL_INSERT_TABLE_CATEGORIA_LANCHE =
            "INSERT INTO " + BD_CATEGORIA_NOME_TABLE + " VALUES ( " +
            "2,'Lanches', 'Coxinha, Pastel,  Esfirra','lanches')";

    public static final String SQL_INSERT_TABLE_CATEGORIA_PETISCO =
            "INSERT INTO " + BD_CATEGORIA_NOME_TABLE + " VALUES ( " +
                    "3,'Petiscos','Batata frita, carne de sol, bisteca, macaxeira','porcoes')";

    public static final String SQL_INSERT_TABLE_CATEGORIA_BEBIDA =
            "INSERT INTO " + BD_CATEGORIA_NOME_TABLE + " VALUES ( " +
                    "4,'Bebidas', 'Refrigerantes, sucos e vitaminas','bebidas')";

    public static final String SQL_INSERT_TABLE_CATEGORIA_SOBREMESA =
            "INSERT INTO " + BD_CATEGORIA_NOME_TABLE + " VALUES ( " +
                    "5,'Sobremesas', 'Torta, pudim, mousse, sorvete','sobremesa')";

    public static final String SQL_INSERT_TABLE_CATEGORIA_HAMBURGUERES =
            "INSERT INTO " + BD_CATEGORIA_NOME_TABLE + " VALUES ( " +
                    "6,'Hambugueres', 'X-Bacon, X-Calabresa, X-Frango','hamburguer')";

    public static final String SQL_INSERT_TABLE_CATEGORIA_TODAS =
            "INSERT INTO " + BD_CATEGORIA_NOME_TABLE + " VALUES ( " +
                    "7,'Todas', 'Todos os produtos','all')";

}

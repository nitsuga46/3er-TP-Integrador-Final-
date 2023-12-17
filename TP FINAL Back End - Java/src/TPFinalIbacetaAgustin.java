import java.sql.*;
import java.util.Scanner;
import java.io.*;
import java.nio.file.*;

public class TPFinalIbacetaAgustin {

    
    public static void main(String[] args) throws Exception {
                
                //Seccion De CONECCION con la base de datos
                Class.forName("com.mysql.cj.jdbc.Driver");
		String  url="jdbc:mysql://localhost:3306/oradores?characterEncoding=utf8";
		String username="root";
		String password="admin";
		Connection con =DriverManager.getConnection(url,username,password);
                //FIN Seccion De CONECCION con la base de datos
                
                Statement stmt =con.createStatement();
		ResultSet rs;
		PreparedStatement st;
		
		int id,edad,op;
		String nombre,apellido,ciudad;
                String qry="";
                
                Scanner in = new Scanner(System.in);
		Scanner str = new Scanner(System.in);
                
                do{
                    System.out.println("------Menú de opciones CRUD MySQL------");
                    System.out.println("1-Insertar Orador");
                    System.out.println("2-Actualizar Orador");
                    System.out.println("3-Eliminar Orador");
                    System.out.println("4-Ver lista de Oradores y crear XML");
                    System.out.println("0-----SALIR-----");
                    System.out.print("Ingrese una opción: ");
		    op= in.nextInt();
                    switch (op){
                        case 1:
				System.out.println("Ingrese Nombre: ");
				nombre=str.nextLine();
                                System.out.println("Ingrese Apelligo: ");
                                apellido=str.nextLine();
				System.out.println("Ingrese Edad: ");
				edad=in.nextInt();
				System.out.println("Ingrese Ciudad: ");
				ciudad=str.nextLine();
				
				qry="insert into orador (nombre,apellido,edad,ciudad) values(?,?,?,?)";
				st= con.prepareStatement(qry);
				st.setString(1, nombre);
                                st.setString(2,apellido);
				st.setInt(3, edad);
				st.setString(4, ciudad);
				
				st.executeUpdate();
				System.out.println("Orador INSERTADO");
				break;
                                
                        case 2:
                                System.out.println("Ingrese ID: ");
				id=in.nextInt();
				System.out.println("Ingrese Nombre: ");
				nombre=str.nextLine();
                                System.out.println("Ingrese Apellido:");
                                apellido=str.nextLine();
				System.out.println("Ingrese Edad: ");
				edad=in.nextInt();
				System.out.println("Ingrese Ciudad: ");
				ciudad=str.nextLine();
				
				qry="update orador set nombre=?,apellido=?,edad=?,ciudad=? where id=?";
				st= con.prepareStatement(qry);
				
				st.setString(1, nombre);
                                st.setString(2,apellido);
				st.setInt(3, edad);
				st.setString(4, ciudad);
				st.setInt(5, id);
				st.executeUpdate();
				System.out.println("Orador MODIFICADO");
				break;
                        case 3:
                                System.out.println("Ingrese ID del Orador a Eliminar : ");
				id=in.nextInt();
				
				qry="delete from orador where id=?";
				st= con.prepareStatement(qry);
				st.setInt(1, id);
				
				st.executeUpdate();
				System.out.println("-----Orador ELIMINADO-----");
				break;
                        case 4:
                                System.out.println("4. IMPRIME TODOS LOS REGISTROS");
				qry="SELECT id,nombre,apellido,edad,ciudad from orador";
				rs=stmt.executeQuery(qry);
                                
                                String filePath = "E:\\Documentos y Programas\\Cursos\\Codo a Codo\\1er Curso (FullStack JAVA)\\Back-End\\TP FINAL\\TP Final IbacetaAgustin\\oradores.xml";
                                Path path = Paths.get(filePath);
                                Files.delete(path);
                                
                                String line = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";  //cabecera de mi archivo XML
                                FileWriter cb = new FileWriter(filePath, true);
                                cb.write(line);
                                cb.close();
                                
                                line = "<oradores>"; //abro el XML
                                FileWriter ap = new FileWriter(filePath, true);
                                ap.write(line);
                                ap.close();
				
                                while(rs.next())
				{
					id=rs.getInt("id");
					nombre=rs.getString("nombre");
                                        apellido=rs.getString("apellido");
					edad=rs.getInt("edad");
					ciudad=rs.getString("ciudad");
					
                                        System.out.print(" || ");
					System.out.print(id+" || ");
					System.out.print(nombre+" || ");
                                        System.out.print(apellido+" || ");
					System.out.print(edad+" || ");
					System.out.println(ciudad+" || ");
                                        
                                        line = "<orador><nombre>" 
                                        + rs.getString("Nombre") + "</nombre><apellido>" 
                                        + rs.getString("Apellido") + "</apellido><edad>" 
                                        + rs.getString("Edad") + "</edad><ciudad>" 
                                        + rs.getString("Ciudad") + "</ciudad></orador>";
           
                                        FileWriter fw = new FileWriter(filePath, true);
                                        fw.write(line);
                                        fw.close();   
				} 
                                
                                System.out.println("ARCHIVO XML CREADO");
                                line = "</oradores>";
                                FileWriter fo = new FileWriter(filePath, true);
                                fo.write(line);
                                fo.close();
				break;
                        case 0:
                                System.out.println("-----MUCHAS GRACIAS-----");
				System.exit(0);
				break;
                        default:
                                System.out.println("Opcion ingresada INCORRECTA");
				break;
                    }
                    
                }while(true);
        }
    }

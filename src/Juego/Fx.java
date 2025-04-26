package Juego;

import Movimientos.*;



//importamos lo que vamos a utilizar de la librería de JavaFx
import Movimientos.Movimientos;
import Pokemons.Bulbasaur;
import Pokemons.Pikachu;
import javafx.animation.PauseTransition;
import javafx.application.Application; // La clase de application de JavaFx
import javafx.geometry.HPos; //Posicion en GridPane
import javafx.geometry.Insets; //Para poder poner Margenes y Padding
import javafx.geometry.Pos; //Para poder Alinear o cambiar de posicion
import javafx.scene.Scene; // Es para poder llamar a la Escena que es lo que podemos visualizar
import javafx.scene.control.Label; // Para mostrar Texto
import javafx.scene.control.ProgressBar; //Es para poder mostrar las barras de vida
import javafx.scene.control.TextArea; // es el Area del texto en el que se va ver lo que sucede en la batalla pokemon
import javafx.scene.image.Image; //Para poder poner las imagenes
import javafx.scene.image.ImageView; // Para visualizar las imagenes que hayamos subido
import javafx.scene.layout.*; //Contiene todos los layouts como BorderPane, vbox etc.
import javafx.scene.text.Font; // Para utilizar una fuente en el texto ya sea Arial o Calibri
import javafx.stage.Stage; //Es el stage osea nuestra ventana principal
import javafx.util.Duration;

import java.util.List;
import java.util.Random; //Cosas al azar

//Nuestra clase principal para poder ver los Graficos que creamos
//Usamos Extends para usar todo de la paqueteria de Java FX con Application
public class Fx extends Application {
    //Definimos nuestras variables
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon atacante;
    private Pokemon defensor;
    private Random random = new Random();
    private boolean peleaTerminada = false;
//Nuestras Variables para nuestros elementos Graficos
    private ImageView imagen1 , imagen2; //Para nuestras imagenes
    private Label labelNombre1, labelNombre2; // Para el texto de nuestros nombres y tipos
    private Label labelHp1, labelHp2; // Para que el texto de la Hp se muestre
    private ProgressBar barradevidaHp1, barradevidaHp2; // Para que se muestre la barra de vida de los pokemones
    private TextArea Batalla; //Para que se muestre el cuadro de texto de la batalla

    public void start (Stage stage) {
        //Los objetos Pokemon y los Movimientos para nuestra batalla
        pokemon1 = new Pikachu();
        pokemon2 = new Bulbasaur();
        Movimientos placaje = new Placaje();
        Movimientos impactrueno = new Impactrueno();
        Movimientos latigocepa = new LatigoCepa();


        //Elementos de la interfaz grafica
        Batalla = new TextArea(); //Creamos el Area del Texto
        Batalla.setEditable(false); //Para que no podamos escribir en la area del texto
        Batalla.setWrapText(true); //Se salta de linea el texto de manera automatica
        Batalla.setFont(Font.font("Verdana", 12)); //Establecemos una fuente
        Batalla.setPrefHeight(150); //La altura

        //Para enseñar los Movimientos a los Pokemones
        Batalla.appendText(pokemon1.aprenderMovimiento(impactrueno)+"\n");
        Batalla.appendText(pokemon1.aprenderMovimiento(placaje)+"\n");
        Batalla.appendText(pokemon2.aprenderMovimiento(latigocepa)+"\n");
        Batalla.appendText(pokemon2.aprenderMovimiento(placaje)+"\n");

        //Configuracion del Layout para la visualizacion de nuestra interfaz
        stage.setTitle("Batalla Pokemon"); //Titulo de la Ventana
        BorderPane layoutPrincipal = configuracion(); //Crear nuestra estructura con un metodo
        layoutPrincipal.setBottom(Batalla); //EL area del texto estara en la parte inferior
        BorderPane.setMargin(Batalla,new Insets(10,0,0,0)); //Margen alrededor del Area del Texto

        //Crear nuestra escena y mostrarla para nosotros
        Scene escena = new Scene(layoutPrincipal,550,450); //Definimos escena para poder utilizarla y definimos su tamaño del Layout principal
        stage.setScene(escena); //Ponemos la Escena principal en la ventana o nuestro stage
        actualizarInterfaz();//Aqui ponemos los Datos como Hp etc.
        stage.show(); // Iniciamos para que muestre nuestra ventana

        iniciarPelea(); //Empieza el combate Pokemon

    }
//Configuramos nuestro Layout Principal para nuestra pelea Pokemon
    private BorderPane configuracion() {
        BorderPane layoutPrincipal = new BorderPane();
        layoutPrincipal.setPadding(new Insets(10));//El espacio del interior


    /*
    El espacio de nuestro primer pokemon que esta en la parte de abajo en la izquierda
    utilizamos vbox que ordena los elementos de manera vertical. el 5 es el espacio entre ellos
     */
        VBox Zona1 = new VBox(5);
        Zona1.setAlignment(Pos.CENTER_LEFT); //Alineamos nuestro contenido
        labelNombre1 = new Label(pokemon1.getNombre() + "[" + pokemon1.getTipo().getTipoPokemon()+"]"); //Etiqueta del Texto
        labelNombre1.setFont(Font.font("Verdana", 16)); //La fuente de texto con la que va aparecer
        barradevidaHp1 = new ProgressBar(); //Creamos la barra de vida
        barradevidaHp1.setMaxWidth(Double.MAX_VALUE); //Hacemos que se expanda Horizontalmente
        barradevidaHp1.setStyle("-fx-accent: Green;"); //Ponemos el color inicial que en este caso es Verde
        labelHp1 = new Label(); //Etiqueta para la barra de vida que se va actualizar
        /*
        Cargamos el archivo de la imagen que se va mostrar para nuestros respectivos Pokemones y se muestra en ImagenView
         */
        try {
            imagen1 = new ImageView(new Image(pokemon1.getImagenPokemon(),120,120,true,true)); //EL TAMAÑO DE 120 * 120 PIXELES
        } catch (Exception e) { //Para capturar el error
            Batalla.appendText("Error en la imagen: " + e.getMessage()+"\n");
            imagen1 = new ImageView(); imagen1.setFitHeight(120); imagen1.setFitWidth(120); //Carga un cuandro Vacio en caso de no tener imagen
        }
        //Atributos que creamos los añadimos al Vbox
        Zona1.getChildren().addAll(labelNombre1, barradevidaHp1, labelHp1);

        // La zona 2 del pokemon

        VBox Zona2 = new VBox(5);
        Zona2.setAlignment(Pos.CENTER_RIGHT); //Alineamos nuestro contenido
        labelNombre2 = new Label(pokemon2.getNombre() + "[" + pokemon2.getTipo().getTipoPokemon()+"]"); //Etiqueta del Texto
        labelNombre2.setFont(Font.font("Verdana", 16)); //La fuente de texto con la que va aparecer
        barradevidaHp2 = new ProgressBar(); //Creamos la barra de vida
        barradevidaHp2.setMaxWidth(Double.MAX_VALUE); //Hacemos que se expanda Horizontalmente
        barradevidaHp2.setStyle("-fx-accent: Green;"); //Ponemos el color inicial que en este caso es Verde
        labelHp2 = new Label(); //Etiqueta para la barra de vida que se va actualizar
        /*
        Cargamos el archivo de la imagen que se va mostrar para nuestros respectivos Pokemones y se muestra en ImagenView
         */
        try {
            imagen2 = new ImageView(new Image(pokemon2.getImagenPokemon(),120,120,true,true)); //EL TAMAÑO DE 120 * 120 PIXELES
        } catch (Exception e) { //Para capturar el error
            Batalla.appendText("Error en la imagen: " + e.getMessage()+"\n");
            imagen2 = new ImageView(); imagen2.setFitHeight(120); imagen2.setFitWidth(120); //Carga un cuandro Vacio en caso de no tener imagen
        }
        //Atributos que creamos los añadimos al Vbox
        Zona2.getChildren().addAll(labelNombre2, barradevidaHp2, labelHp2);

        //El campo de batalla Pokemon
        //GridPane acomoda los elementos en Filas y Columnas
        GridPane campoBatalla = new GridPane();
        campoBatalla.setHgap(20); //Espacio horizontal entre la Celda
        campoBatalla.setVgap(10); //Espacio Vertical entre la fila
        campoBatalla.setAlignment(Pos.CENTER); //Centramos la Cuadricula

        //StackPane sirve para poner elementos encima de otros, en este caso vamos a poner las imagenes encima

        StackPane Sprite1 = new StackPane(imagen1); Sprite1.setAlignment(Pos.CENTER);
        StackPane Sprite2 = new StackPane(imagen2); Sprite2.setAlignment(Pos.CENTER);

        //Añadimos nuestros elementos al GridPane
        campoBatalla.add(Zona1,1,1); //El pokemon 1 esta en la celda 1,1
        campoBatalla.add(Sprite1,0,1); //El sprite del Pokemon1 en la celda 0,1
        campoBatalla.add(Zona2,0,0); //Pokemon 2 en la celda 0,0
        campoBatalla.add(Sprite2,1,0); //El sprite del Pokemon2 en la celda 1,0

        //Alineamos las imagenes adrentro de las celdas del Gridpane
        GridPane.setHalignment(Sprite1, HPos.LEFT);
        GridPane.setHalignment(Sprite2, HPos.RIGHT);

        //Configuramos las Columnas de GridPane para que no ocupen mucho espacio
        ColumnConstraints columna1 = new ColumnConstraints(); columna1.setPercentWidth(50);
        ColumnConstraints columna2 = new ColumnConstraints(); columna2.setPercentWidth(50);
        campoBatalla.getColumnConstraints().addAll(columna1,columna2);

        //Ponemos GridPane al centro de Nuestro BorderPane
        layoutPrincipal.setCenter(campoBatalla);
        return layoutPrincipal; //El layout Completo
    }

    //Actualizamos los Datos de nuestros elementos que ya estan definidos, con datos nuevos o que se actualizaron
    private void actualizarInterfaz() {
        //Actualizacion de la barra de vida de los Pokemones en la batalla
        double actualizarHp1= (pokemon1.getHpMax()>0) ? (double)pokemon1.getHpActual() / pokemon1.getHpMax(): 0.0;
        barradevidaHp1.setProgress(actualizarHp1); //Se actualiza Nuestra barra de vida
        labelHp1.setText("HP: " + pokemon1.getHpActual() + "/" + pokemon1.getHpMax()); //Se actualiza el texto de nuestra Hp

        //Para cambiar el color de la barra de Vida de nuestra pelea Pokemon
        if (actualizarHp1 <= 0.25) barradevidaHp1.setStyle("-fx-accent: Red;");
        else if (actualizarHp1 <0.5) barradevidaHp1.setStyle("-fx-accent: yellow;");
        else barradevidaHp1.setStyle("-fx-accent: green;");

        //Pokemon 2

        double actualizarHp2= (pokemon2.getHpMax()>0) ? (double)pokemon2.getHpActual() / pokemon2.getHpMax(): 0.0;
        barradevidaHp2.setProgress(actualizarHp2); //Se actualiza Nuestra barra de vida
        labelHp2.setText("HP: " + pokemon2.getHpActual() + "/" + pokemon2.getHpMax()); //Se actualiza el texto de nuestra Hp

        //Para cambiar el color de la barra de Vida de nuestra pelea Pokemon
        if (actualizarHp2 <= 0.25) barradevidaHp2.setStyle("-fx-accent: Red;");
        else if (actualizarHp2 <0.5) barradevidaHp2.setStyle("-fx-accent: yellow;");
        else barradevidaHp2.setStyle("-fx-accent: green;");

        //La informacion de la batalla avanza automaticamente
        Batalla.setScrollTop(Double.MAX_VALUE);

    }

    //El primer turno de la pelea
    private void iniciarPelea() {
        Batalla.appendText("---------¡La Batalla con el Entrenador SirRaZGriz a iniciado!--------\n" + pokemon1.getNombre() +" Vs " + pokemon2.getNombre() + "\n--------------------------------\n\n");
        atacante = pokemon1; //El pokemon 1 inicia la pelea
        defensor = pokemon2; //El pokemon 2 recibe los ataques
        peleaTerminada = false;

        //Pause.Transition crea una pequeña pausa entre los turnos y Duration.seconds define cuanto tiempo dura esa pausa de la pelea
        PauseTransition pausa = new PauseTransition(Duration.seconds(1.5));
        //setOnFinished define que evento se va desencadenar cuando la pequeña pausa termine
        //Evento es lo que desencadena lo que va pasar cuando termine la pausa con un metodo
        pausa.setOnFinished(evento -> ejecutarTurnoSiguiente());
        pausa.play(); //Inicia la Pausa
    }

    /*
    Es lo que hace que va pasar en el siguiente turno
    que pokemon ataca o que es lo que sucede en la batalla
     */
    private void ejecutarTurnoSiguiente() {
        if (peleaTerminada) return; //Si la pelea termino no va hacer nada

        Batalla.appendText("-----Turno del otro entrenador " + atacante.getNombre() + "-------\n");

        //Lista de movimientos de los atacantes
        List<Movimientos> movimientos = atacante.getMovimientos();
        if (!movimientos.isEmpty()) { //Si tiene movimientos
            //Por Ahora se elige el movimiento aleatoriamentes pero todo esto lo vamos a cambiar
            Movimientos movimientoElegido = movimientos.get(random.nextInt(movimientos.size()));
            //Llamamos a nuestro metodo usar para poder usar nuestros movimientos
            String turno = movimientoElegido.usar(atacante, defensor);
            Batalla.appendText(turno + "\n"); //Se muestra el resultado en nuestra area de texto
            actualizarInterfaz(); //Se actualizan la barras de vida entre otras cosas

            //Si el pokemon fue debilitado pasaria esto
            if(!defensor.vivo()) {
                Batalla.appendText("\n¡"+ defensor.getNombre() + "¡Ha sido Debilitado!\n");
                peleaTerminada(atacante); //Termina la batalla y el atacante gano la batalla
                return; //Se termina la batalla
            }
        }
            else {
                Batalla.appendText(atacante.getNombre() + "No tiene Movimientos!\n");
            }
            //El cambio de turno de los pokemones
        Pokemon cambio = atacante;
            atacante = defensor;
            defensor = cambio;

            Batalla.appendText("\n"); //Separar turnos

        //Otra pausa entre turnos
        PauseTransition pausa = new PauseTransition(Duration.seconds(2.5));
        pausa.setOnFinished(evento -> ejecutarTurnoSiguiente());
        pausa.play();
    }

    /*Finalizamos la batalla con este metodo
    y se muestran los resultados de la batalla
     */
    public void peleaTerminada(Pokemon ganador) {
        peleaTerminada = true; //Detener los turnos
        Batalla.appendText("\n------¡FIN DE LA BATALLA!-----");
        if (ganador != null) {
            Batalla.appendText("!"+ganador.getNombre()+" Gana la batalla!\n ");
        } else {
            Batalla.appendText(" ¡Es un empate! ");
        }
        Batalla.appendText("---------------------\n");
        actualizarInterfaz(); //Se actualiza la interfaz
    }
    /*
    Finalizamos con nuestro metodo main y llamamos a launch que es para
    llamar al metodo star que utiliza a javaFx
     */
    public static void main(String[] args) {
        launch(args);
    }
}

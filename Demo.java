import java.awt.Desktop;
import java.awt.Desktop.*;
import java.io.*;
import java.net.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.media.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.stage.FileChooser.*;
import javafx.util.*;

public class Demo extends Application{
	public static FileChooser fileChooser;
	public static Stage stage;
	public static Scene scene;
	public static boolean flag;
	public static Circle circle;
	public static Button play, stop;
	public static MediaPlayer mediaPlayer;//Prevent GC from killing media
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		flag = true;
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Demo.fxml"));
		VBox vbox = loader.<VBox>load();
		Scene primaryScene = new Scene(vbox);
		scene = primaryScene;
		
		circle = (Circle)scene.getRoot().lookup("#circle");
		play = (Button)scene.getRoot().lookup("#play");
		stop = (Button)scene.getRoot().lookup("#stop");
		
		primaryStage.setTitle("GUI Demo Code");
		primaryStage.setScene(primaryScene);
		stage.getIcons().add(new Image("open-iconic-master/png/aperture-2x.png"));
		primaryStage.show();
		
		//Add music
		try{
			String musicFile = "Handel_Water_Music_Air.mp3";
			Media sound = new Media(new File(musicFile).toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@FXML
	public void exit(Event e){
		System.exit(0);
	}
	
	@FXML
	public void open(Event e){
		try{
			File file = fileChooser.showOpenDialog(stage);
			InputStream input = new FileInputStream(file);
			ImageView photoView = (ImageView)scene.getRoot().lookup("#photo");
			photoView.setImage(new Image(input));
		} catch(FileNotFoundException fnfe){
			fnfe.printStackTrace();
		} catch(NullPointerException npe){
			// cancel
		}
	}
	
	@FXML
	public void keyTyped(Event e){
		Text keyText = (Text)scene.getRoot().lookup("#keytext");
		keyText.setText(((KeyEvent)e).getCharacter());
	}
	
	@FXML
	public void browser(Event e){
		String url = "http://www.google.com";
		if(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)){
			Desktop desktop = Desktop.getDesktop();
			try{
				desktop.browse(new URI(url));
			}catch(IOException ioe){
				ioe.printStackTrace();
			}catch(URISyntaxException urise) {
				urise.printStackTrace();
			}
		}else{
			Runtime runtime = Runtime.getRuntime();
			try{
				runtime.exec("xdg-open " + url);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	@FXML
	public void info(Event e){
		Alert alert = new Alert(AlertType.INFORMATION, "This is demo code.");
		alert.setTitle("About");
		alert.setHeaderText("Demo code");
		alert.show();
	}
	
	@FXML
	public void flip(Event e){
		if(flag){
			circle.setFill(Color.INDIGO);
		} else{
			circle.setFill(Color.ORCHID);
		}
		flag = !flag;
	}
	
	@FXML
	public void play(Event e){
		try{
			mediaPlayer.play();
		}catch(Exception ex){
		}
		play.setDisable(true);
		stop.setDisable(false);
	}
	
	@FXML
	public void stop(Event e){
		try{
			mediaPlayer.stop();
		}catch(Exception ex){
		}
		stop.setDisable(true);
		play.setDisable(false);
	}
}

/* All the links!!!
 * http://tutorials.jenkov.com/javafx/fxml.html#:~:text=In%20order%20to%20load%20an%20FXML%20file%20and,the%20FXML%20file%20must%20be%20located%20at%20C:datahello-world.fxml.
 * https://docs.oracle.com/javafx/2/api/javafx/fxml/doc-files/introduction_to_fxml.html
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Parent.html#lookup-java.lang.String-
 * http://www.java2s.com/example/java/javafx/get-javafx-node-by-id.html
 * https://useiconic.com/open
 * https://www.tutorialspoint.com/javafx/javafx_quick_guide.htm
 * https://www.tutorialspoint.com/javafx-example-to-set-action-behavior-to-the-menu-item
 * http://tutorials.jenkov.com/javafx/menubar.html
 * https://www.tutorialspoint.com/how-to-create-a-menubar-in-javafx
 * https://www.geeksforgeeks.org/javafx-menubar-and-menu/
 * https://stackoverflow.com/questions/9408244/how-to-set-vbox-size-to-window-size-in-javafx
 * http://www.java2s.com/Code/Java/JDK-6/UsingtheDesktopclasstolaunchaURLwithdefaultbrowser.htm
 * https://www.geeksforgeeks.org/javafx-alert-with-examples/
 * https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
 * https://stackoverflow.com/questions/10121991/javafx-application-icon
 * https://examples.javacodegeeks.com/desktop-java/javafx/javafx-input-event-example/
 * https://examples.javacodegeeks.com/desktop-java/javafx/javafx-input-event-example/
 * https://stackoverflow.com/questions/5226212/how-to-open-the-default-webbrowser-using-java
 * https://stackoverflow.com/questions/23202272/how-to-play-sounds-with-javafx
 * https://stackoverflow.com/questions/29870368/javafx-mediaplayer-music-stops-after-10-seconds
 * https://commons.wikimedia.org/wiki/File:5-George_Frideric_Handel_-_Water_Music_Suite_in_F_major_(Air)_HWV348.ogg
 */

package logic;

import controlers.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;
import static  logic.Coordinates.getX;
import static logic.Coordinates.getY;

public class gameLogic {
    private String[][] textures=new String[20][20];
    private String[][] details=new String[20][20];
    private String[][] characters=new String[20][20];
    private Coordinates currentLocation=new Coordinates(0, 0);

    public int getLastX() {
        return lastX;
    }

    public int getLastY() {
        return lastY;
    }

    private int lastX=0;
    private int lastY=0;

    public gameLogic() throws FileNotFoundException {
        mapBuilder();
    }



    public void moveLeft() throws FileNotFoundException {
        if(canMoveTo(getX()-1, currentLocation.getY())){
            lastX=getX();
            lastY=getY();
            characters[getX()][getY()]="0";
            currentLocation.setX(currentLocation.getX()-1);
            currentLocation.setY(currentLocation.getY() );
            characters[getX()][getY()]="@";
        }
    }

    public void moveRight() throws FileNotFoundException {
        if(canMoveTo(getX()+1, currentLocation.getY())){
            lastX=getX();
            lastY=getY();
            characters[getX()][getY()]="0";
            currentLocation.setX(currentLocation.getX()+1);
            currentLocation.setY(currentLocation.getY() );
            characters[getX()][getY()]="@";
        }
    }

    public void moveUp() throws FileNotFoundException {
        if(canMoveTo(getX(), currentLocation.getY()-1)){
            lastX=getX();
            lastY=getY();
            characters[getX()][getY()]="0";
            currentLocation.setX(currentLocation.getX());
            currentLocation.setY(currentLocation.getY()-1 );
            characters[getX()][getY()]="@";
        }
    }

    public void moveDown() throws FileNotFoundException {
        if (canMoveTo(getX(), currentLocation.getY() + 1)) {
            lastX=getX();
            lastY=getY();
            characters[getX()][getY()] = "0";
            currentLocation.setX(currentLocation.getX());
            currentLocation.setY(currentLocation.getY() + 1);
            characters[getX()][getY()] = "@";
        }
    }

    private boolean canMoveTo(int x, int y){
        if(characters[x][y]=="0"){
            if(details[x][y].equals("0") && textures[x][y].equals("G") || textures[x][y].equals("S")
                    || textures[x][y].equals("D")   ){
                return true;
            }
        }
        return false;
    }

    private void mapBuilder(){
        try {
            texturesLoad();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            detailsLoad();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            charactersLoad();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void texturesLoad() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapTexture.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                switch (icon){
                    case "G": textures[x][y]="G";
                        break;
                    case "L": textures[x][y]="L";
                        break;
                    case "W": textures[x][y]="W";
                        break;
                    case "S": textures[x][y]="S";
                        break;
                    case "D": textures[x][y]="D";
                        break;
                }
            }
        }
    }
    private void detailsLoad() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapDetails.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                switch (icon){
                    case "T": details[x][y]="T";
                        break;
                    case "C": details[x][y]="C";
                        break;
                    default: details[x][y]="0";
                }
            }
        }
    }
    private void charactersLoad() throws FileNotFoundException {
        File file=new File(Paths.get("src", "main", "resources", "textures/mapCharacters.txt").toUri());
        Scanner reader=new Scanner(file);
        for(int y=0; y<20;y++){
            for(int x=0;x<20;x++){
                String icon=reader.next();
                switch (icon){
                    case "@": {
                        characters[x][y]="@";
                        currentLocation=new Coordinates(x, y);
                    }
                    break;
                    case "!": characters[x][y]="!";
                        break;
                    case "*": characters[x][y]="*";
                        break;
                    default: characters[x][y]="0";
                }
            }
        }
    }

    public Coordinates getCurrentLocation() {
        return currentLocation;
    }
    public String[][] getTextures() {
        return textures;
    }
    public String[][] getDetails() {
        return details;
    }
    public String[][] getCharacters() {
        return characters;
    }
}
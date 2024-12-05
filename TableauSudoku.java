import javax.swing.*;
import java.awt.*;

import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

import java.awt.Cursor;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;




public class TableauSudoku extends JPanel {
    
    private JTextField[][] listeTxt;
    private int largeurTxt;
    private int hauteurTxt;
    private int margeTxt;
    private int taillePoliceTxt;
    private Color panelBackground;
    private Color txtBackground1;
    private Color txtForeground1;
    private Color txtBackground2;
    private Color txtForeground2;
    private Color txtBackground3;
    private Color txtForeground3;
    private Color txtForeground4;
    private Color txtBackground4;

    private Sudoku sudoku;
    private ArrayList<JTextField> listeTxtAux;
    private ArrayList<JTextField> listeTxtGenerés;
    
    public TableauSudoku() {
        initialiserComposants();
        creerSudoku();
    }
    
    public void initialiserComposants() {
        listeTxt = new JTextField[9][9];
        largeurTxt = 66;
        hauteurTxt = 66;
        margeTxt = 4;
        taillePoliceTxt = 27;
        panelBackground = Color.BLACK;
        txtBackground1 = Color.WHITE;
        txtForeground1  = Color.BLACK;
        txtBackground2 = Color.WHITE;
        txtForeground2 = Color.BLACK;
        txtBackground3 =  Color.WHITE;
        txtForeground3  = Color.BLACK;
        txtBackground4 = Color.BLUE;
        txtForeground4 = Color.WHITE;

        sudoku = new Sudoku();
        listeTxtAux = new ArrayList<>();
        listeTxtGenerés = new ArrayList<>();
    }

    public void creerSudoku() {
        this.setLayout(null);
        this.setPreferredSize(new Dimension(largeurTxt * 9 + (margeTxt * 4), hauteurTxt * 9 + (margeTxt * 4)));
        this.setBackground(panelBackground);
        crearComposTxt();
        
    }

    public void crearComposTxt(){
        int x = margeTxt;
        int y = margeTxt;
    
        for(int i = 0 ; i < listeTxt.length; i++){
            for(int j = 0; j < listeTxt[0].length; j++){
    
                JTextField txt = new JTextField();
                this.add(txt);
                txt.setBounds(x , y , largeurTxt, hauteurTxt);
                txt.setBackground(txtBackground1);
                txt.setForeground(txtForeground1);
                txt.setFont(new Font("Montserrat" , Font.BOLD,taillePoliceTxt));
                txt.setEditable(false);
                txt.setCursor(new Cursor(Cursor.HAND_CURSOR));
                txt.setBorder(BorderFactory.createLineBorder(panelBackground,1));
                txt.setVisible(true);
                x+=largeurTxt;
    
                if((j + 1)%3==0){
                    x+=margeTxt;
                }
                listeTxt[i][j] = txt;
                genereEvents(txt);
            }
            x=margeTxt;
            y+=hauteurTxt;
            if((i + 1)%3==0){
                y+=margeTxt;
            }
               
        }
    }
    public boolean PasModifiable(JTextField txt){
        for(JTextField jTxt : listeTxtGenerés){
            if(txt == jTxt){
                return true;
            }
        }
        return false;

    }
    
    public void genereEvents(JTextField txt){
        MouseListener evento = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    // Votre code pour la logique lorsque la souris est cliquée
                }
            
                @Override
                public void mouseEntered(MouseEvent e) {
                    // Votre code pour la logique lorsque la souris entre dans le composant
                }
            
                @Override
                public void mouseExited(MouseEvent e) {
                    // Votre code pour la logique lorsque la souris sort du composant
                }
            
                @Override
                public void mousePressed(MouseEvent e) {
                    // Votre code pour la logique lorsque le bouton de la souris est enfoncé
                    pressed(txt);
                }
            
                @Override
                public void mouseReleased(MouseEvent e) {
                    // Votre code pour la logique lorsque le bouton de la souris est relâché
                }
            };
            KeyListener eventoTecla = new KeyListener() {

                @Override
                public void keyTyped(KeyEvent e) {
                  
                }

                @Override
                public void keyPressed(KeyEvent e) {

                    if(PasModifiable(txt)){
                        return;
                    }
                    char keyPressed = e.getKeyChar();
                    String currentText = txt.getText().trim(); // Récupérer le texte actuel dans la case et supprimer les espaces
                    
                    // Si le caractère est un chiffre de 1 à 9
                    if (keyPressed >= '1' && keyPressed <= '9') {
                        // Si la longueur actuelle est inférieure à 7 (pour permettre jusqu'à quatre chiffres et trois espaces)
                        if (currentText.length() < 7) {
                            // Si le texte est vide, ajouter simplement le chiffre
                            if (currentText.isEmpty()) {
                                txt.setText(String.valueOf(keyPressed));
                            } else if (currentText.endsWith(" ")) {
                                // Si le texte se termine par un espace, ajouter le chiffre après un espace
                                txt.setText(currentText + keyPressed);
                            } else {
                                // Si le texte contient déjà des chiffres, ajouter le chiffre après un espace
                                txt.setText(currentText + " " + keyPressed);
                            }
                        }
                    } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                        // Si la touche de suppression est enfoncée et le texte n'est pas vide
                        if (!currentText.isEmpty()) {
                            // Supprimer le dernier caractère
                            txt.setText(currentText.substring(0, currentText.length() - 1));
                        }
                    }
                }
                
                
                
                
                


                @Override
                public void keyReleased(KeyEvent e) {
                   
                }
                
            };
            txt.addMouseListener(evento);
            txt.addKeyListener(eventoTecla);

    }
        

        public void pressed(JTextField txt){

            for(JTextField jTxt : listeTxtAux){
                jTxt.setBackground(txtBackground1);
                jTxt.setForeground(txtForeground1);
                jTxt.setBorder(BorderFactory.createLineBorder(panelBackground, 1));
        
            }
            listeTxtAux.clear();

            for(JTextField jTxt : listeTxtGenerés){
                jTxt.setBackground(txtBackground4);
                jTxt.setForeground(txtForeground4);
            }


            for(int i = 0; i < listeTxt.length; i++){
                for(int j = 0; j < listeTxt[0].length; j++){
                    if(listeTxt[i][j] == txt){
                        // Mettre à jour la couleur de fond et la couleur du texte de la ligne sélectionnée
                        for(int k = 0; k < listeTxt.length; k++){
                            listeTxt[i][k].setBackground(txtBackground2);
                            listeTxt[i][k].setForeground(txtForeground2);
                            listeTxtAux.add(listeTxt[k][j]);
                        }
                        
                        // Mettre à jour la couleur de fond et la couleur du texte de la colonne sélectionnée
                        for(int k = 0; k < listeTxt[0].length; k++){
                            listeTxt[k][j].setBackground(txtBackground2);
                            listeTxt[k][j].setForeground(txtForeground2);
                            listeTxtAux.add(listeTxt[i][k]);
                        }
                        int positionI = sudoku.sousGrilleActuelle(i);
                        int positionJ = sudoku.sousGrilleActuelle(j);
                        for ( int k = positionI-3 ; k < positionI; k++){
                            for(int l = positionJ - 3; l < positionJ; l++){
                                listeTxt[k][l].setBackground(txtBackground2);
                                listeTxt[k][l].setForeground(txtForeground2);
                                listeTxtAux.add(listeTxt[k][l]);
                            }
                        }
                        // Mettre à jour la couleur de fond et la couleur du texte de la case sélectionnée
                        txt.setBackground(txtBackground3);
                        txt.setForeground(txtForeground3);
                        txt.setBorder(BorderFactory.createLineBorder(Color.WHITE,2));
                        return;
                    }
                }
            }
        }

    
        public void nettoyerTxt() {
            NettoyerTxt nettoyeur = new NettoyerTxt(listeTxt, listeTxtGenerés, txtBackground1, txtForeground1, panelBackground);
            nettoyeur.NettoyerTxt();
        }
   

    public boolean CreerPartieSudoku() {
        return CreerPartieSudoku.creerPartie(this);
    }
    
    
    public Sudoku getSudoku() {
        return sudoku;
    }
    
    public ArrayList<JTextField> getListeTxtGeneres() {
        return listeTxtGenerés;
    }
    

    public JTextField[][] getListeTxt() {
        return listeTxt;
    }
    
    public void setListeTxt(JTextField[][] listeTxt) {
        this.listeTxt = listeTxt;
    }
    
    public int getLargeurTxt() {
        return largeurTxt;
    }
    
    public void setLargeurTxt(int largeurTxt) {
        this.largeurTxt = largeurTxt;
    }
    
    public int getHauteurTxt() {
        return hauteurTxt;
    }
    
    public void setHauteurTxt(int hauteurTxt) {
        this.hauteurTxt = hauteurTxt;
    }
    
    public int getMargeTxt() {
        return margeTxt;
    }
    
    public void setMargeTxt(int margeTxt) {
        this.margeTxt = margeTxt;
    }
    
    public int getTaillePoliceTxt() {
        return taillePoliceTxt;
    }
    
    public void setTaillePoliceTxt(int taillePoliceTxt) {
        this.taillePoliceTxt = taillePoliceTxt;
    }
    
    public Color getPanelBackground(){
        return panelBackground;
    }
    public void  setPanelBackground(Color panelBackground){
        this.panelBackground = panelBackground;
    }

    public Color getTxtBackground1(){
        return txtBackground1;
    }
    public void setTxtBackground1(Color txtBackground1){
        this.txtBackground1 = txtBackground1;

    }
    public Color getTxtForeground1(){
        return txtForeground1;
    }
    public void setTxtForeground1(Color txtForeground1){
        this.txtForeground1 = txtForeground1;
        
    }
    
    public Color getTxtBackground2(){
        return txtBackground2;
    }
    public void setTxtBackground2(Color txtBackground2){
        this.txtBackground2 = txtBackground2;

    }
    public Color getTxtForeground2(){
        return txtForeground1;
    }
    public void setTxtForeground2(Color txtForeground2){
        this.txtForeground2 = txtForeground2;
        
    }
    
    public Color getTxtBackground3(){
        return txtBackground3;
    }
    public void setTxtBackground3(Color txtBackground3){
        this.txtBackground3 = txtBackground3;

    }
    public Color getTxtForeground3(){
        return txtForeground3;
    }
    public void setTxtForeground3(Color txtForeground3){
        this.txtForeground3 = txtForeground3;
        
    }
    public Color getTxtBackground4(){
        return txtBackground4;
    }
    public void setTxtBackground4(Color txtBackground4){
        this.txtBackground4 = txtBackground4;

    }
    public Color getTxtForeground4(){
        return txtForeground4;
    }
    public void setTxtForeground4(Color txtForeground4){
        this.txtForeground4 = txtForeground4;
        
    }
}
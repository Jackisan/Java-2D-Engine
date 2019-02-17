package com.editor;

import com.engine.*;

import javax.swing.*;

public class Editor {

    static Input input = new Input();
    static SceneView sceneView;

    public static void main(String[] args){

        new Window();
        Handler.inEditor = true;
        sceneView = new SceneView();
        Window.frame.add(sceneView);
        sceneView.setBounds(20,20,600,600);

        //Menu bar for loading/saving scenes and more
        JMenuBar bar = new JMenuBar();

        //Button in the menubar for the saving/loading
        JMenu fileMenu = new JMenu("File");
        JButton loadSceneButton = new JButton("Load");

        JFileChooser fileChooser = new JFileChooser();
        loadSceneButton.addActionListener(actionEvent -> {
            int returnVal = fileChooser.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                SceneManager.loadSceneWithPath(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });

        fileMenu.add(loadSceneButton);

        JButton newSceneButton = new JButton("New");
        newSceneButton.addActionListener(actionEvent -> {

            System.out.println("Making a new scene");

            Scene scene = new Scene("C://Users//jackson.eggerd//Documents//Game Engine Saves//Scene");
            SceneManager.setCurrentScene(scene);
            GameObject g = new GameObject("Test Object", new Vector(0,0));
            g.setObjectRenderer(new ObjectRenderer(g));
        });

        fileMenu.add(newSceneButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(actionEvent -> {
            SceneManager.getCurrentScene().save();

        });
        fileMenu.add(saveButton);

        JButton saveAsButton = new JButton("Save As");
        saveAsButton.addActionListener(actionEvent -> {
            int returnVal = fileChooser.showSaveDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                SceneManager.getCurrentScene().saveTo(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        fileMenu.add(saveAsButton);

        bar.add(fileMenu);

        Window.frame.setJMenuBar(bar);
        Window.showFrame();
    }
}

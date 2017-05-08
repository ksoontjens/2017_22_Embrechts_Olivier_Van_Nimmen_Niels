package hellotvxlet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.tv.xlet.*;
import org.dvb.ui.DVBColor;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HStaticText;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;


public class HelloTVXlet implements Xlet, HActionListener {
    private HScene scene;
    
    DVBColor activeGreen = new DVBColor(0, 255, 0, 255);
    DVBColor activeRed = new DVBColor(255, 0, 0, 255);
    DVBColor activeYellow = new DVBColor(255, 255, 0, 255);
    DVBColor activeBlue = new DVBColor(0, 0, 255, 255);
    
    DVBColor staticGreen = new DVBColor(0, 255, 0, 100);
    DVBColor staticRed = new DVBColor(255, 0, 0, 100);
    DVBColor staticYellow = new DVBColor(255, 255, 0, 100);
    DVBColor staticBlue = new DVBColor(0, 0, 255, 100);
    
    List cpuColors = new ArrayList();
    List userColors = new ArrayList();
    
    
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) {
      scene=HSceneFactory.getInstance().getDefaultHScene();
      MijnComponent mc=new MijnComponent(100,100);
      scene.add(mc);
      // SD D1   270 x 576
      
      HStaticText info=new HStaticText("Let's play simon says",100,0,500,76); // x,y,b,h
      info.setBackgroundMode(HVisible.BACKGROUND_FILL);
      info.setBackground(Color.BLACK);
      scene.add(info);
      
      
      HTextButton buttonGreen=new HTextButton("",100,76,250,250);
      buttonGreen.setBackgroundMode(HVisible.BACKGROUND_FILL);
      buttonGreen.setBackground(staticGreen);
      scene.add(buttonGreen);
      
      HTextButton buttonRed=new HTextButton("",350,76,250,250);
      buttonRed.setBackgroundMode(HVisible.BACKGROUND_FILL);
      buttonRed.setBackground(staticRed);
      scene.add(buttonRed);
      
      HTextButton buttonYellow=new HTextButton("",100,326,250,250);
      buttonYellow.setBackgroundMode(HVisible.BACKGROUND_FILL);
      buttonYellow.setBackground(staticYellow);
      scene.add(buttonYellow);
      
      HTextButton buttonBlue=new HTextButton("",350,326,250,250);
      buttonBlue.setBackgroundMode(HVisible.BACKGROUND_FILL);
      buttonBlue.setBackground(staticBlue);
      scene.add(buttonBlue);
      
      
      
      HStaticText background=new HStaticText("",0,0,1000,1000); // x,y,b,h
      background.setBackgroundMode(HVisible.BACKGROUND_FILL);
      background.setBackground(Color.BLACK);
      scene.add(background);
      
      
      
      buttonGreen.setFocusTraversal(null, buttonYellow, null, buttonRed); // up,down,left,right
      buttonRed.setFocusTraversal(null, buttonBlue, buttonGreen, null);
      buttonYellow.setFocusTraversal(buttonGreen, null, null, buttonBlue);
      buttonBlue.setFocusTraversal(buttonRed, null, buttonYellow,  null);
      buttonGreen.requestFocus();
      
      buttonGreen.setActionCommand("buttonGreen");
      buttonRed.setActionCommand("buttonRed");
      buttonYellow.setActionCommand("buttonYellow");
      buttonBlue.setActionCommand("buttonBlue");
      buttonGreen.addHActionListener(this); // bovenaan toevoegen achter XLet, HActionListener
                                        // dan import + implement all abstract methods
      buttonRed.addHActionListener(this);
      buttonYellow.addHActionListener(this);
      buttonBlue.addHActionListener(this);
      
      
      scene.validate(); scene.setVisible(true);
      
    }

    public void startXlet() {
        
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }
    
    public void determineColors() {
        
        Random r = new Random();
        int Low = 1;
        int High = 4;
        int Result = r.nextInt(High-Low) + Low;
        
        switch(Result)
        {
            case 1:
                cpuColors.add("Green");
                break;
                
            case 2:
                cpuColors.add("Red");
                break;
                
            case 3:
                cpuColors.add("Yellow");
                break;
                
            case 4:
                cpuColors.add("Blue");
                break;
        }
        
    }
    
    public void showColors() {
        
        for (int i = 0; i < cpuColors.size(); i++)
        {
            if(cpuColors.get(i) == "Green")
            {
                HTextButton buttonGreen=new HTextButton("",100,76,250,250);
                buttonGreen.setBackgroundMode(HVisible.BACKGROUND_FILL);
                buttonGreen.setBackground(staticGreen);
                scene.add(buttonGreen);
                scene.popToFront(buttonGreen);
      
                HTextButton buttonRed=new HTextButton("",350,76,250,250);
                buttonRed.setBackgroundMode(HVisible.BACKGROUND_FILL);
                buttonRed.setBackground(staticRed);
                scene.add(buttonRed);
                scene.popToFront(buttonRed);
      
                HTextButton buttonYellow=new HTextButton("",100,326,250,250);
                buttonYellow.setBackgroundMode(HVisible.BACKGROUND_FILL);
                buttonYellow.setBackground(staticYellow);
                scene.add(buttonYellow);
                scene.popToFront(buttonYellow);
      
                HTextButton buttonBlue=new HTextButton("",350,326,250,250);
                buttonBlue.setBackgroundMode(HVisible.BACKGROUND_FILL);
                buttonBlue.setBackground(staticBlue);
                scene.add(buttonBlue);
                scene.popToFront(buttonBlue);
                    
            }
            scene.repaint();
        }
    }

    public void actionPerformed(ActionEvent arg0) {
        System.out.println(arg0.getActionCommand());
        
        
        for (int i = 0; i < cpuColors.size(); i++)
        {
            if(cpuColors.get(i) == userColors.get(i))
            {
                //Goed
            }
        }
            
        
        
        if(arg0.getActionCommand().equals("buttonYellow"))
        {
            HStaticText info=new HStaticText("Correct",100,0,500,76);
            info.setBackground(Color.BLACK);
            info.setBackgroundMode(HVisible.BACKGROUND_FILL);
            scene.add(info);
            scene.popToFront(info);
            scene.repaint();
        }
        else
        {
            HStaticText info=new HStaticText("Fout",100,0,500,76);
            info.setBackground(Color.BLACK);
            info.setBackgroundMode(HVisible.BACKGROUND_FILL);
            scene.add(info);
            scene.popToFront(info);
            scene.repaint();
        }
    }
}

import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class tetris_one_block_15 extends PApplet {

//[2016/11/21_1] 1. \ubc30\uc5f4 \uc0ac\uc6a9\ud574\uc11c \u3134 \uadf8\ub9ac\uae30

//[2016/11/21_2] 1. \ube14\ub7ed \ubc11\uc5d0 \ubcbd\uc774 \uc788\uc73c\uba74 \uba48\ucd94\ub3c4\ub85d 

//[2016/11/21_3] 1. \ube14\ub7ed \ubc11\uc774 \ubcbd\uc774\uba74 \uba48\ucd94\uace0 \uacb9\uce58\ub294 \uac1c\uc218 \ud655\uc778

//[2016/11/21_4] 1. \ube14\ub7ed \ubc11\uc774 \ubcbd\uc774\uba74 \uba48\ucd94\uace0 \uadf8 \uc790\ub9ac\uc5d0 1\uc744 \ucc44\uc6b4\ub2e4

//[2016/11/21_5] 1. \ud55c\uc904\uc774 \ucc28\uba74 \uadf8 \uc904\uc744 \uc9c0\uc6b0\uace0 \ubc30\uc5f4\uc744 \ub0b4\ub9b0\ub2e4

//[2016/11/21_6] 1. \ub2e4\ub978 \ube14\ub7ed \ubaa8\uc591 \ucd94\uac00 

//[2016/11/21_7] 1. \ub79c\ub364\uc73c\ub85c \ube14\ub7ed\ubaa8\uc591\uc774 \ubc14\ub00c\ub3c4\ub85d

//[2016/11/22_1] 1. \uc790\uc138\ud55c \uc8fc\uc11d \ub2ec\uae30
//               2. \ub2e4\uc2dc \ub5a8\uc5b4\uc9c8\ub54c \ub5a8\uc5b4\uc9c0\ub294 \uc704\uce58\uac00 \uc911\uac04\uc774 \ub418\ub3c4\ub85d
//               3. \ub2e4\uc2dc \ub5a8\uc5b4\uc9c8\ub54c \ub35c\uc5b4\uc9c0\ub294 \ube14\ub7ed\uc758 \ubc29\ud5a5\uc774 \ucd08\uae30\uac12\uc774 \ub418\ub3c4\ub85d
//               4. \ube14\ub7ed \uc0c9\uae54 \ub123\uae30
//               5. \ube14\ub7ed\uc774 \uc313\uc774\uba74 \ube14\ub7ed\uc758 \uc0c9\uae54\uc774 \ub0a8\uc544\uc788\ub3c4\ub85d
//               6. \ud55c\uc904\uc774 \ucc28\uc11c \uc904\uc744 \ub0b4\ub9ac\uba74 \ube14\ub7ed\uc758 \uc0c9\uae54\uc774 \uc720\uc9c0 \ub418\uba74\uc11c \ub0b4\ub824\uc624\ub3c4\ub85d 

//[2016/11/22_2] 1. \ube14\ub7ed\uc774 \uc88c,\uc6b0\ub85c \uc774\ub3d9\ud560\ub54c \uc606\uc774 \uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud574\uc11c \uba48\ucd94\ub3c4\ub85d

//[2016/11/22_3] 1. \ubc30\uacbd \ubc14\uafc8

//[2016/11/22_4] 1. \ub2e4\uc74c\uc5d0 \ub5a8\uc5b4\uc9c8 \ube14\ub7ed \ub098\uc634

//[2016/11/22_5] 1. \uc810\uc218 \ub098\uc634
//               2. Level \uc18d\ub3c4 \ubcc0\ud654  




PImage img;  // Declare variable "a" of type PImage
PImage score_num_1;
PImage score_num_2;
PImage score_num_3;
PFont font;
String score_string;

int x=3; // X\uc704\uce58 
int y=2; // Y\uc704\uce58
int leftright_del=0; // \uc88c,\uc6b0\ub85c \uc774\ub3d9\ud558\uba74 \uc9c0\uc6b8\ub54c \uc0ac\uc6a9 
int count=0;
int i;  // \u3134 \uadf8\ub9b4\ub54c X
int j;  // \u3134 \uadf8\ub9b4\ub54c Y
int a;  // \ubc30\uacbd\uacfc \u3134\uc774 \uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9(X)
int b;  // \ubc30\uacbd\uacfc \u3134\uc774 \uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9(Y)
int one_count=0; //1\uc774 \uba87\uac1c\uc778\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9
int two_count=0; //\uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9 
int block_type=0; //\ube14\ub7ed \ubc29\ud5a5 
int block_type_del=0; //\ube14\ub7ed \ubc29\ud5a5 \uc9c0\uc6b0\uae30
int change_block=0; //\ub2e4\ub978 \ube14\ub7ed \ubaa8\uc591 
float r; // \ub79c\ub364 
int leftright_two=0; // \ube14\ub7ed\uc774 \uc88c,\uc6b0\ub85c \uc774\ub3d9\ud560\ub54c \uc606\uc774 \uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9
int q=0;  // \ube14\ub7ed\uc774 \uc88c,\uc6b0\ub85c \uc774\ub3d9\ud560\ub54c \uc606\uc774 \uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9
int background_b_c=0; // \ubc30\uacbd\uc758 \uc0c9\uae54\uac12\uc744 \uc77d\uc5b4\uc62c\ub54c \uc0ac\uc6a9 
int left=0; // \ube14\ub7ed\uc774 \uc88c\ub85c \uc774\ub3d9\ud560\ub54c \uc606\uc774 \uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9
int right=0;  // \ube14\ub7ed\uc774 \uc6b0\ub85c \uc774\ub3d9\ud560\ub54c \uc606\uc774 \uacb9\uce58\ub294\uc9c0 \ud655\uc778\ud560\ub54c \uc0ac\uc6a9
int block_drop=0; // \ub5a8\uc5b4\uc9c8 \ube14\ub7ed
int score=0;  //\uc810\uc218 
int level=1; //Level
int speed=30; //\uc18d\ub3c4 
int x_offset=0; //\ub5a8\uc5b4\uc9c8 \ube14\ub7ed X \uc704\uce58 
int y_offset=0; //\ub5a8\uc5b4\uc9c8 \ube14\ub7ed Y \uc704\uce58 

int default_offset=3;

 String s;


 //\ubc30\uacbd
 char[][] table = { {1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},//1
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1},
                    {1,0,0,0,0,0,0,0,0,0,0,1,1}, //20
                    {1,1,1,1,1,1,1,1,1,1,1,1,1},
                    {1,1,1,1,1,1,1,1,1,1,1,1,1} };

//\ube14\ub7ed\ub4e4 
char[][][][] block = { {{{0,0,0,0}, // * 
                         {0,1,0,0}, // ***
                         {0,1,1,1},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,1,0},
                         {0,1,0,0},
                         {0,1,0,0}},
                        {{0,0,0,0},
                         {1,1,1,0},
                         {0,0,1,0},
                         {0,0,0,0}},
                        {{0,0,1,0},
                         {0,0,1,0},
                         {0,1,1,0},
                         {0,0,0,0}}},
   
                       {{{0,0,0,0}, //   *
                         {0,0,1,0}, // ***
                         {1,1,1,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,0,0},
                         {0,1,0,0},
                         {0,1,1,0}},
                        {{0,0,0,0},
                         {0,1,1,1},
                         {0,1,0,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,1,0},
                         {0,0,1,0},
                         {0,0,1,0}}},
   
                       {{{0,0,0,0}, //  *
                         {0,1,0,0}, // ***
                         {1,1,1,0},
                         {0,0,0,0}},
                        {{0,1,0,0},
                         {0,1,1,0},
                         {0,1,0,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,1,1},
                         {0,0,1,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,0,1,0},
                         {0,1,1,0},
                         {0,0,1,0}}},

                       {{{0,0,0,0}, //  **
                         {0,1,1,0}, // **
                         {1,1,0,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,0,0},
                         {0,1,1,0},
                         {0,0,1,0}},
                        {{0,0,0,0},
                         {0,1,1,0},
                         {1,1,0,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,0,0},
                         {0,1,1,0},
                         {0,0,1,0}}},

                       {{{0,0,0,0}, // ****
                         {0,0,0,0}, 
                         {1,1,1,1},
                         {0,0,0,0}},
                        {{0,1,0,0},
                         {0,1,0,0},
                         {0,1,0,0},
                         {0,1,0,0}},
                        {{0,0,0,0},
                         {0,0,0,0},
                         {1,1,1,1},
                         {0,0,0,0}},
                        {{0,1,0,0},
                         {0,1,0,0},
                         {0,1,0,0},
                         {0,1,0,0}}},
   
                       {{{0,0,0,0}, // **
                         {0,1,1,0}, // **
                         {0,1,1,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,1,0},
                         {0,1,1,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,1,0},
                         {0,1,1,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {0,1,1,0},
                         {0,1,1,0},
                         {0,0,0,0}}},

                       {{{0,0,0,0}, // **
                         {1,1,0,0}, //  **
                         {0,1,1,0},
                         {0,0,0,0}},
                        {{0,0,1,0},
                         {0,1,1,0},
                         {0,1,0,0},
                         {0,0,0,0}},
                        {{0,0,0,0},
                         {1,1,0,0},
                         {0,1,1,0},
                         {0,0,0,0}},
                        {{0,0,1,0},
                         {0,1,1,0},
                         {0,1,0,0},
                         {0,0,0,0}}} };
             
 
public void setup() {
  
  // The image file must be in the data folder of the current sketch 
  // to load successfully
  img = loadImage("tetris_background.jpg");  // Load the image into the program  
  image(img, 0, 0);
  score_num_1 = loadImage("score_1.jpg");  // Load the image into the program  
  image(score_num_1, 392, 491);

  font = loadFont("ArialNarrow-Bold-48.vlw");
  textFont(font);


  score_string = String.valueOf(0);
  textSize(30);
  text(score_string, 779, 555);


    //fill(255,0,0);
    //rect(3,3,18,18);
  frameRate(30);

  //\ubc30\uacbd \ube14\ub7ed\uc744 \uadf8\ub9b0\ub2e4
  for(j=1;j<22;j++){
    for(i=1;i<11;i++){
      if(table[j][i]>1){
          stroke(255, 255, 255);
          fill(255,0,0);
          rect(default_offset + (i-1)*22+484,default_offset + (j-1)*22+105,19,19);
      }
    }
  }



}

public void draw() {
 
  if(count==speed){
    count=0;

    y++;
    //change_block=0;

     move_down();
  }

  if(score>=200){
    level=3;
  }
  else if(score>=100){
    level=2;
  }
  else{
    level=1;
  }

  if(level==1){
    speed=30;

    score_num_1 = loadImage("score_1.jpg");  // Load the image into the program  
    image(score_num_1, 392, 491);
  }
  else if(level==2){
    speed=20;

    score_num_2 = loadImage("score_2.jpg");  // Load the image into the program  
    image(score_num_2, 390, 490);
  }
  else if(level==3){
    speed=10;

    score_num_3 = loadImage("score_3.jpg");  // Load the image into the program  
    image(score_num_3, 391, 491);
  }

  count++;
  block_drop_func();


  //delay(30);
}

public void keyPressed() {

  if(key == CODED)
  {
    if (keyCode == LEFT)  //\uc88c 
    {

      leftright_del++;

      x--;
      if(x<-1){
        x=-1;
      }

      leftright_move();
    }
    if(keyCode == RIGHT)  //\uc6b0 
    {
      
      leftright_del--;

      x++;
      if(x>7){
        x=7;
      }
      
      leftright_move();
    }
    if(keyCode == UP) //\uc0c1 
    {
        //\ube14\ub7ed\uc758 \ubc29\ud5a5\uc774 \ubc14\ub014\ub54c \uadf8 \uc804\uc758 \ubaa8\uc591\uc744 \uc9c0\uc6b0\ub3c4\ub85d \ucd08\uae30\uac12\uc744 \ub123\uc5b4\uc900\ub2e4
        block_type_del=block_type;
        block_type++;

        if(block_type>3){
          block_type=0;
        }
        block_type_del_func();
        block_type_func();

    }
    if(keyCode == DOWN) //\ud558 
    {
       y++;

       move_down();
    }      
  }


}

public void leftright_move() {

  //\uc88c,\uc6b0 \uacb9\uce58\ub294\uc9c0 \ud655\uc778
  for(b=0;b<4;b++){
    for(a=0;a<4;a++){
      if(table[b+(y+1)][a+(x+1)]>=1 && block[change_block][block_type][b][a]==1){
          q++;
      }
    }
  }
  
  //\uacb9\uce58\uc9c0 \uc54a\ub294 \uacbd\uc6b0 
  if(q==0){
    leftright_func();
  }
  //\uacb9\uce58\ub294 \uacbd\uc6b0
  else{
    x = x+leftright_del;
    q=0;
  }

    // stroke(255, 255, 255);
    // fill(255,255,255);
    // rect(default_offset + (x+leftright_del)*22,default_offset+(y)*22,19,19);

  

    // stroke(255, 255, 255);
    // fill(255,0,0);
    // rect(default_offset + (x+leftright_del)*22,default_offset+(y)*22,19,19);

}

public void leftright_func() {


    //\uc88c,\uc6b0 \uc9c0\uc6b4\ub2e4  
    for(j=0;j<4;j++){
      for(i=0;i<4;i++){
        if(block[change_block][block_type][j][i]==1){
            stroke(255, 255, 255);
            fill(255,255,255);
            rect(default_offset + (i+x+leftright_del)*22+484,default_offset+(j+y)*22+105,19,19);
         
        }
      }
    }
    
    leftright_del=0;
    
    //\uc88c,\uc6b0 \uadf8\ub9b0\ub2e4 
    draw_block();

}


public void move_down() {

  //\uacb9\uce58\ub294\uc9c0 \ud655\uc778
  for(b=0;b<4;b++){         
    for(a=0;a<4;a++){      
      if(table[b+(y+1)][a+(x+1)]>0 && block[change_block][block_type][b][a]==1){
        two_count++;
      } 
    } 
  } 
  //System.out.println("two_count : "+two_count);

  //\uacb9\uce58\ub294 \uacbd\uc6b0 
  if(two_count>0){
    System.out.print(level);
    //System.out.print("two_count : "+two_count);



      //\uacb9\uce58\ub294 \uacbd\uc6b0 \ubc30\uacbd \ubc30\uc5f4\uc5d0 \ube14\ub7ed\uc758 \uc0c9\uae54\uac12\uc744 \ucc44\uc6b4\ub2e4
         background_1();

      
      for(j=1;j<21;j++){

        //\ud55c\uc904\uc5d0 \ube14\ub7ed\uc774 \uba87\uac1c\uc778\uc9c0 \ud655\uc778
        for(i=1;i<11;i++){
          if(table[j][i] >0){
            one_count++;   
          }
        }
          //System.out.print(one_count+" ");

          //\ud55c\uc904\uc774 \ucc28\uba74
          if(one_count==10){
            score = score+10;

            stroke(4, 51, 141);
            fill(4,51,141);
            rect(default_offset +770, default_offset + 527,70,25);

            score_string = String.valueOf(score);
            fill(255,255,255);
            textSize(30);
            text(score_string, 779, 555);

            
           // System.out.print(j+" ");
              
              //\ud55c\uc904 \uc9c0\uc6b4\ub2e4
              for(a=0;a<10;a++){
                stroke(255, 255, 255);
                fill(255,255,255);
                rect(default_offset + a*22+484,default_offset + (y+1)*22+105,19,19);
              }

              //\ud55c\uc904\uc774 \ucc28\uba74 \ubc30\uc5f4\uc744 \uc544\ub798\ub85c \ubcf5\uc0ac\ud55c\ub2e4
              for(b=j;b>1;b--){
                for(a=1;a<11;a++){
                  table[b][a]=table[b-1][a];
                }
              }

               

           }

          one_count=0;
          x=3;

          //\ube14\ub7ed\uc774 \ub2e4\uc2dc \ub5a8\uc5b4\uc9c8\ub54c \ucc98\uc74c\uc758 \ubaa8\uc591\uc774 \ub418\ub3c4\ub85d
          block_type=0;


      }

        //\uc0c8\ub85c \uadf8\ub9b0\ub2e4
         line_color();
         background_color();

      
        System.out.println(" ");

    two_count=0;
    y=2;

    r = random(100);
    //System.out.println(int(r%7));
    change_block=block_drop;

    block_drop=(int)(r%7); 

    block_drop_del_func();

  }
  else{

      //\ube14\ub7ed \uc9c0\uc6b0\uae30
      for(j=0;j<4;j++){
        for(i=0;i<4;i++){
          if(block[change_block][block_type][j][i]==1){
              stroke(255, 255, 255);
              fill(255,255,255);
              rect(default_offset + (i+x)*22+484,default_offset+(j+y-1)*22+105,19,19);
          }
        }
      }
    
      //\ube14\ub7ed \uadf8\ub9ac\uae30,\ube14\ub7ed\ubcc4 \uc0c9\uae54 
      draw_block();
  }


}

public void block_type_del_func(){
      //\ube14\ub7ed \uc9c0\uc6b0\uae30
      for(j=0;j<4;j++){
        for(i=0;i<4;i++){
          if(block[change_block][block_type_del][j][i]==1){
              stroke(255, 255, 255);
              fill(255,255,255);
              rect(default_offset + (i+x)*22+484,default_offset+(j+y)*22+105,19,19);
          }
        }
      }
}

public void block_type_func(){
      //\ube14\ub7ed \uadf8\ub9ac\uae30
      draw_block();
}



public void draw_block() {

      //\ube14\ub7ed \uadf8\ub9ac\uae30,\ube14\ub7ed\ubcc4 \uc0c9\uae54 
      for(j=0;j<4;j++){
        for(i=0;i<4;i++){
          if(block[change_block][block_type][j][i]==1){
            stroke(255, 255, 255);
            b_c(change_block+2);
            rect(default_offset + (i+x)*22+484,default_offset+(j+y)*22+105,19,19);     
          }
          // else if(change_block==0){
          //     stroke(255, 255, 255);
          //     b_c(0);
          //     rect(default_offset + (i+x)*22,default_offset+(j+y)*22,19,19);
          // }
        }
      }
}

public void background_1() {
      

      for(j=0;j<4;j++){
        for(i=0;i<4;i++){
          if(block[change_block][block_type][j][i]==1){
                table[j+(y)][i+(x+1)]=(char)(change_block+2);
          } 
        }
      }
}


public void b_c(int color_value){
  if(color_value == 0)
    fill(255,255,255);
  else if(color_value == 2)
    fill(255,0,0);
  else if(color_value == 3)
    fill(0,0,255);
  else if(color_value == 4)
    fill(0,255,0);
  else if(color_value == 5)
    fill(255,255,0);
  else if(color_value == 6)
    fill(255,0,255);
  else if(color_value == 7)
    fill(0,255,255);
  else if(color_value == 8)
    fill(0,200,255);

}

public void line_color() {

  int line_value=0;

      for(j=0;j<4;j++){
        for(i=0;i<4;i++){
          line_value=table[j+(y+1)][i+(x+1)];
            if(line_value>1){
              stroke(255, 255, 255);
              b_c(line_value);
              rect(default_offset + (i+x)*22+484,default_offset+(j+y)*22+105,19,19);
            }
            // else{
            //   stroke(255, 255, 255);
            //   b_c(0);
            //   rect(default_offset + (i+x)*22+484,default_offset+(j+y)*22+105,19,19);
            // }
          }
        }
    }


public void background_color() {

  int background_value=0;

      for(j=1;j<21;j++){
        for(i=1;i<11;i++){
          background_value=table[j][i];
            if(background_value>1){
              stroke(255, 255, 255);
              b_c(background_value);
              rect(default_offset + (i-1)*22+484,default_offset+(j-1)*22+105,19,19);
            }
            else{
              stroke(255, 255, 255);
              b_c(0);
              rect(default_offset + (i-1)*22+484,default_offset+(j-1)*22+105,19,19);
            }
          }
        }
    }


public void block_drop_func()  {


    if(block_drop==0){
      x_offset=732;
      y_offset=115;
    }
    else if(block_drop==1){
      x_offset=750;
      y_offset=115;
    }
    else if(block_drop==2){
      x_offset=750;
      y_offset=115;
    }
    else if(block_drop==3){
      x_offset=750;
      y_offset=115;
    }
    else if(block_drop==4){
      x_offset=740;
      y_offset=100;
    }
    else if(block_drop==5){
      x_offset=740;
      y_offset=115;
    }
    else if(block_drop==6){
      x_offset=750;
      y_offset=115;
    }


      //\ub5a8\uc5b4\uc9c8 \ube14\ub7ed \uadf8\ub9ac\uae30 
      for(j=0;j<4;j++){
        for(i=0;i<4;i++){
          if(block[block_drop][0][j][i]==1){
            stroke(255, 255, 255);
            b_c(block_drop+2);
            rect(default_offset + (i)*22+x_offset,default_offset+(j)*22+y_offset,19,19);     
          }
        }
      }
}

public void block_drop_del_func()  {
      //\ub5a8\uc5b4\uc9c8 \ube14\ub7ed \uc9c0\uc6b0\uae30  
      for(j=0;j<4;j++){
        for(i=0;i<4;i++){
          if(block[block_drop][0][j][i]>=0){
            stroke(2, 45, 113);
            fill(2,45,113);
            rect(default_offset + (i)+730,default_offset+(j)+115,105,83);     
          }
        }
      }
}
  public void settings() {  size(1193, 652); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "tetris_one_block_15" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}

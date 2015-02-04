package com.Tomlomon;

import java.util.LinkedList;
import java.util.Random;

public class ColorGen {

  //set hex will take in hex value
  //harshness
    //how much deviated from original value
    //takes floating point percentage of half
    //always down, not up
  //ceilings
    //cei
    //only works with generating random values
    //ceiling for each R G B colors, can choose individuals

  //next colors: gives you next pretty colors
  //next pruificuation: drives towards dominate value
  //next shades: making pretty of the same color
    //give it a hex, give it a ceiling,

  private LinkedList<String[]> previousColors; //LL Storing all previous color arrays.
  private LinkedList<Byte> previousSteps;//LL Storing all previous step amounts.

  private String [] currentColors;//AL Storing current scheme.

  private String currentBaseColor;//STR Storing the current HEX base.
  private byte currentSteps;//BYTE Containing current step amount;

  private byte domRGB;//BYTE Containing the dominant color r(1)g(2)b(3).
  private float harsh;//FLOAT Containing the multiplier for how much currentBaseColor's valRGB will change.

  private short [] cei = new short [3];

  //Next: printColors, isLight, isDark, Lighten, Darken,  saveColors, loadColors

  public ColorGen(){
    reset();
  }

  @Override
  public String toString(){
    if (currentColors==null){
      return null;
    }
    String cat = "";
    for (int i = 0; i < currentSteps; i++){
      cat+=currentColors[i]+'\n';
    }
    return cat;
  }

  public void reset(){
    previousColors = new LinkedList<String[]>();
    previousSteps = new LinkedList<Byte>();

    currentColors = null;

    randomValues();
  }

  public void randomValues(){

    Random rGen = new Random();

    for (int i = 0; i < cei.length; i++){
      cei[i] = (short) rGen.nextInt(256);
    }

    genRandomHex();
    currentSteps = (byte)rGen.nextInt(10);

    domRGB = calcDominant(currentBaseColor);
    harsh = (((float)(rGen.nextInt(99)+2))/100);
  }

  //takes a hex value color and creates intervals
  //for R G or B and then splices them together and
  //spits them out

  public String[] nextColors(){
    if (currentColors != null){
      previousColors.add(currentColors);
    }

    String tempColor = reorderHex(currentBaseColor,domRGB);
    short [] intervals = calcIntervals(hexToDec(tempColor.substring(0, 2)));

    currentColors = new String [currentSteps];

    for(int i = 0; i < currentSteps; i++){
      currentColors[i]= toHexString(intervals[i])+tempColor.substring(2, 6);
      currentColors[i] = returnHex(currentColors[i],domRGB);
    }
    previousSteps.add(currentSteps);
    return currentColors;
  }

  public String[] nextPurification(){
    if (currentColors != null){
      previousColors.add(currentColors);
    }

    currentColors = new String[currentSteps];

    String tempColor = reorderHex(currentBaseColor,domRGB);
    short [] intervals1 = calcIntervals(hexToDec(tempColor.substring(2, 4)));
    short [] intervals2 = calcIntervals(hexToDec(tempColor.substring(4, 6)));

    for(int i = 0; i < currentSteps; i++){
      currentColors[i] = tempColor.substring(0, 2) + toHexString(intervals1[i]) + toHexString(intervals2[i]);
      currentColors[i] = returnHex(currentColors[i],domRGB);
    }

    previousSteps.add(currentSteps);
    return currentColors;
  }

  public String[] nextShades(){
    if (currentColors != null){
      previousColors.add(currentColors);
    }

    currentColors = new String[currentSteps];

    short [] intervals0 = calcIntervals(hexToDec(currentBaseColor.substring(0, 2)));
    short [] intervals1 = calcIntervals(hexToDec(currentBaseColor.substring(2, 4)));
    short [] intervals2 = calcIntervals(hexToDec(currentBaseColor.substring(4, 6)));

    for(int i = 0; i < currentSteps; i++){
      currentColors[i] =  toHexString(intervals0[i]) + toHexString(intervals1[i]) + toHexString(intervals2[i]);
    }

    previousSteps.add(currentSteps);
    return currentColors;
  }

  private short[] calcIntervals(short input){

    short [] out = new short[currentSteps];

    for (int i = 0; i < currentSteps; i++){
      out[i]=(short)(input-((input*harsh)/currentSteps*i));
    }

    return out;
  }

  public boolean setBaseCEI(short r, short g, short b){
    if (r > 255 || g > 255 || b > 255 || r < 0 || g < 0 || b < 0){
      return false;
    }
    cei[0]=r;
    cei[1]=g;
    cei[2]=b;
    genRandomHex();
    domRGB = calcDominant(currentBaseColor);
    return true;
  }

  public boolean setBase(String HEX){

    if (HEX.length()!=6){
      return false;
    }
    try
    {
      @SuppressWarnings("unused")
      int value = Integer.parseInt(HEX, 16);
    }
    catch(NumberFormatException nfe)
    {
      System.out.println("not a valid hex");
      return false;
    }
    currentBaseColor = HEX;
    domRGB = calcDominant(currentBaseColor);
    return true;
  }

  public boolean setSteps(byte s){
    if (s<2){
      return false;
    }
    currentSteps = s;
    return true;
  }

  public boolean setEditable(byte dominant){
    if (dominant == 1 || dominant == 2 || dominant == 3){
      domRGB = dominant;
      return true;
    }
    return false;
  }

  public boolean setHarshness(float harshness){
    while (harshness > 1){
      harshness = harshness/10;
    }
    if (harshness < .2){
      return false;
    }
    harsh = harshness;
    return true;
  }

  public String getBase(){
    return currentBaseColor;
  }

  public byte getSteps(){
    return currentSteps;
  }

  public byte getDominant(){
    return domRGB;
  }

  public float getHarshness(){
    return harsh;
  }

  public String[] getColorsAt(int index){
    if (previousColors.size()<index || index < 0){
      return null;
    }
    return previousColors.get(index);
  }

  public String[] getColors(){
    return currentColors;
  }

  private void genRandomHex(){

    Random rGen = new Random();
    String [] rgbRNG = new String [3];

    for (short i = 0; i < rgbRNG.length; i++){
      rgbRNG[i]=(toHexString(rGen.nextInt(cei[i]+1)));
      while (rgbRNG[i].length()<2){
        rgbRNG[i] = 0+rgbRNG[i];
      }
    }
    currentBaseColor = rgbRNG[0]+rgbRNG[1]+rgbRNG[2];
  }

  private String reorderHex(String in, int pullFront){
    LinkedList<String> rgb = new LinkedList<String>();

    for (short i = 0; i < 3; i++){
      if(i==pullFront-1){
        rgb.add(0, in.substring(i*2, (i+1)*2));
      } else {
        rgb.add(in.substring(i*2, (i+1)*2));
      }
    }
    return rgb.get(0)+rgb.get(1)+rgb.get(2);
  }

  private String returnHex(String in, int returnPos){
    LinkedList<String> rgb = new LinkedList<String>();

    String val = in.substring(0,2);

    for (short i = 1; i < 3; i++){
      rgb.add(in.substring(i*2, (i+1)*2));
    }

    rgb.add(returnPos-1, val);

    return rgb.get(0)+rgb.get(1)+rgb.get(2);
  }

  private byte calcDominant(String inColor) {
    Short [] rgbSplit = new Short [3];

    byte index = 0;
    int big = 0;

    for (short i = 0; i < rgbSplit.length; i++){
      rgbSplit [i] = getValuePerRGB(i+1, inColor);

      if (rgbSplit [i]>big){
        big = rgbSplit [i];
        index = (byte)(i+1);
      }
    }
    return index;
  }

  private short getValuePerRGB(int index, String inColorHex){

    String perRGBStr = inColorHex.substring((index-1)*2, (index)*2);
    return (short)hexToDec(perRGBStr);
  }

  private short hexToDec(String hexadecimal){
    int d = Integer.valueOf(hexadecimal, 16);
    return (short)d;
  }

  private String toHexString(int input){
    String out = Integer.toHexString(input);
    while (out.length()<2){
      out = "0"+out;
    }
    return out;
  }
}

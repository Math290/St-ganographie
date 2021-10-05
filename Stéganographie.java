package com.mathpassionkasiski;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Stéganographie {
	public static void main(String[] args){
		//String path = args[0];
		//String texte = args[1];
		//Trouver les images
		//FindImagesInImage(args[0]);
        //FindImagesInImage(args[0]);
		//Hide image in image
		//int width, height;
        //BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        //hideImageInImage(image, args[0]);
		//------Cacher le texte dans une image--------------------//
		//hideTextInImage(args[1], String fileName)
		//----------find text in image
		//findTextInImage(args[0]);
    }
		public static void FindImagesInImage(String fileName) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File(fileName));
			} catch (Exception e) {
				System.out.println("L'image n'existe pas!!!");
			}
			int width = image.getWidth();
			int height = image.getHeight();
			BufferedImage image1 = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			BufferedImage image2 = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			BufferedImage image3 = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Color pixels = new Color(image.getRGB(i, j));
					if (pixels.getRed()%2==1) {
						image1.setRGB(i, j, Color.BLACK.getRGB());
					}
					else {
						image1.setRGB(i, j, Color.WHITE.getRGB());
					}
					if (pixels.getGreen()%2==1) {
						image2.setRGB(i, j, Color.BLACK.getRGB());
					}
					else {
						image2.setRGB(i, j, Color.WHITE.getRGB());
					}
					if (pixels.getBlue()%2==1) {
						image3.setRGB(i, j, Color.BLACK.getRGB());
					}
					else {
						image3.setRGB(i, j, Color.WHITE.getRGB());
					}
				}
		}
			try {
				ImageIO.write(image1,"png" , new File("D://MIAGE//picture211.png"));
				ImageIO.write(image2,"png" , new File("D://MIAGE//picture222.png"));
				ImageIO.write(image3,"png" , new File("D://MIAGE//picture233.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
		public static BufferedImage writeTextInImage(String text, int width, int height)  {
			
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = image.getGraphics();//build image with the graphic method
			graphics.setColor(Color.LIGHT_GRAY);
			graphics.fillRect(0, 0, width, height);// Fills the specified rectangle
			graphics.setColor(Color.BLACK);// Fix Color Black
			graphics.setFont(new Font("Arial Black", Font.ITALIC, 20));//Sets this graphics context's font to the specified font.
			graphics.drawString(text, 10, 25);
			try {
				ImageIO.write(image, "jpg", new File("imagetext-image.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			return image;	
		}	
		public static void hideImageInImage(BufferedImage image, String fileName) {
			BufferedImage HiddingImage = null;
			try {
				HiddingImage = ImageIO.read(new File(fileName));
			} catch (Exception e) {
				System.out.println("L'image n'existe pas!!");
			}
			int width = Math.min( HiddingImage.getWidth(),image.getWidth());
			int height = Math.min(HiddingImage.getHeight(), image.getHeight());
			BufferedImage FinalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					if (image.getRGB(i,j) == Color.BLACK.getRGB()) {
						 FinalImage.setRGB(i, j, HiddingImage.getRGB(i, j) - HiddingImage.getRGB(i, j)%2 + 1);
						
					}
					else {
						FinalImage.setRGB(i, j, HiddingImage.getRGB(i, j) - HiddingImage.getRGB(i, j)%2);
					}	
				}
				try {
					ImageIO.write(FinalImage,"png", new File("FinalImage.png"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		public static void hideTextInImage(String text, String fileName) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File(fileName));
			} catch (Exception e) {
				System.out.println("L'image n'existe pas!");
			}
			int width = image.getWidth();
			int height = image.getHeight();
			BufferedImage FinalImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			int k = 8,index = 0;
			int ascii = (int) text.charAt(index);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Color pixels = new  Color(image.getRGB(i, j));
					if (pixels.getRed()%2==1 && pixels.getBlue()%2==1) {
						if (ascii%2==0) {
							Color comp = new Color(pixels.getRed(), pixels.getBlue(),pixels.getGreen() -pixels.getGreen()%2 + 1);
							FinalImage.setRGB(i, j, comp.getRGB());
							ascii = ascii/2;
						}
						else {
							Color comp = new Color(pixels.getRed(), pixels.getBlue(),pixels.getGreen() -pixels.getGreen()%2);
							FinalImage.setRGB(i, j, comp.getRGB());
							ascii = ascii/2;
							
						}
						 k--;
		                    if (k == 0) {
		                        k = 8;
		                        index++;
		                        if (index >= text.length()) {
		                            index = 0;
		                        }
		                        ascii = (int) text.charAt(index);
		                    }
		                    else {
		                    	FinalImage.setRGB(i, j, pixels.getRGB());
		                    }
		                    
					}
					
				}
			}
			try {
				ImageIO.write(FinalImage,"png",new File("textimage.png"));
			} catch (Exception e) {
				e.printStackTrace();
			}		
		
		}
		public static String findTextInImage(String fileName) {
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File(fileName));
			} catch (Exception e) {
				System.out.println("Impossible de trouver l'image");
			}
			int width = image.getWidth();
			int height = image.getHeight();
			int ascii = 0;
			int index = 0;
			String FinalText = "";
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					Color pixels = new Color(image.getRGB(i, j));
					if (pixels.getBlue()%2==1 && pixels.getRed()%2==1) {
						if (pixels.getGreen()%2==1) {
							ascii += Math.pow(2, index);
						}
						index++;
					}
					if (index==8) {
						FinalText += (char) ascii;
						index = 0;
						ascii = 0;
					}
				}
				
			}
			
			return FinalText;
			
		} 

	}	





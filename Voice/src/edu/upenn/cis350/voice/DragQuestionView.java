package edu.upenn.cis350.voice;

import android.util.AttributeSet;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;

public class DragQuestionView extends View implements VoiceViewI {
	private static RectF square =new RectF(100,200,200,300);
	private static int pic1topx=405;
	private static int pic2topx=695;
	private static int pic3topx=985;
	private static int pic1topy=305;
	private static int pic2topy=205;
	private static int pic3topy=405;
	private static int pic1bottomx=505;
	private static int pic2bottomx=795;
	private static int pic3bottomx=1085;
	private static int pic1bottomy=405;
	private static int pic2bottomy=305;
	private static int pic3bottomy=505;
	private static RectF pic1 = new RectF(pic1topx, pic1topy, pic1bottomx, pic1bottomy);
	private static RectF pic2 = new RectF(pic2topx, pic2topy, pic2bottomx, pic2bottomy);
	private static RectF pic3 = new RectF(pic3topx, pic3topy, pic3bottomx, pic3bottomy);
	private int answerInt=0;
	private static int xtoffset=0;
	private static int ytoffset=0;
	private static int xboffset=0;
	private static int yboffset=0;
	private static boolean is1, is2, is3, isPressed = false;
	
	public DragQuestionView(Context c) {
		super(c);
	}
	
	public DragQuestionView(Context c, AttributeSet a) {
		super(c, a);
	}
	
	/**
	 * Draw the canvas as a soft teal. Check if any of the images are intersecting the answer box.
	 * If they are, change the stored answer to the assigned int value of the image.
	 * Redraw the pictures.
	 * 
	 *  @param- The canvas to draw
	 */
	
	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(153, 255, 255);
		Color boxColor = new Color();
		int colorInt = boxColor.rgb(51,204,204);
		Paint paint = new Paint();
		paint.setColor(colorInt);
		
		if(square.intersect(pic1) ){
			answerInt = 1;
		}
		else if(square.intersect(pic2) ){
			answerInt = 2;
		}
		else if(square.intersect(pic3) ){
			answerInt = 3;
		}
		//Update the x and y coordinates of each image
		square.set(590,400,690,500);
		pic1.set((float)pic1topx, (float)pic1topy, (float)pic1bottomx, (float)pic1bottomy);
		pic2.set((float)pic2topx, (float)pic2topy, (float)pic2bottomx, (float)pic2bottomy);
		pic3.set((float)pic3topx, (float)pic3topy, (float)pic3bottomx, (float)pic3bottomy);
		canvas.drawRect(square, paint);
		
		Bitmap pic = BitmapFactory.decodeResource(this.getResources(), R.drawable.happypup);
		canvas.drawBitmap(pic, null, pic1, paint);
		pic = BitmapFactory.decodeResource(this.getResources(), R.drawable.neutralpup);
		canvas.drawBitmap(pic, null, pic2, paint);
		pic = BitmapFactory.decodeResource(this.getResources(), R.drawable.verysadpup);
		canvas.drawBitmap(pic, null, pic3, paint);
	}
	
	/**
	 * This method makes it so the user can drag each image if its being touched.
	 * 
	 * @param- The motion event being queried
	 * @return- always true
	 */
	
	@Override
	public boolean onTouchEvent (MotionEvent event) {
		int eventAction = event.getAction();
		int actionX = (int)event.getX(event.findPointerIndex(event.getActionIndex()));
		int actionY = (int)event.getY(event.findPointerIndex(event.getActionIndex()));
		//Find the offset between the edges and the spot its touched, so that each image is consistently dragged from the spot it was touched.
		if(eventAction ==0 ){
			if(actionX < pic1bottomx && actionY < pic1bottomy && actionX > pic1topx && actionY > pic1topy){
				xtoffset = pic1topx -actionX;
				ytoffset = pic1topy - actionY;
				xboffset =  pic1bottomx-actionX;
				yboffset = pic1bottomy-actionY ;
				is1 = true;
				invalidate();}
			else if(actionX < pic2bottomx && actionY < pic2bottomy && actionX > pic2topx && actionY > pic2topy){
				xtoffset = pic2topx -actionX;
				ytoffset = pic2topy - actionY;
				xboffset =  pic2bottomx-actionX;
				yboffset = pic2bottomy-actionY ;
				is2=true;
				invalidate();}
			else if(actionX < pic3bottomx && actionY < pic3bottomy && actionX > pic3topx && actionY > pic3topy){
				xtoffset = pic3topx -actionX;
				ytoffset = pic3topy - actionY;
				xboffset =  pic3bottomx-actionX;
				yboffset = pic3bottomy-actionY ;
				is3=true;
				invalidate();}
			isPressed = true;
		}
		//Apply the offset to the x and y values of the point of contact, and change the x and y coordinates of the touched image
		if(event.getPressure()!=0 && isPressed){
			if(is1){
				pic1topx = actionX+ xtoffset;
				pic1topy = actionY+ ytoffset;
				pic1bottomx = actionX+ xboffset;
				pic1bottomy = actionY+ yboffset;
				invalidate();}
			else if(is2){
				pic2topx = actionX+ xtoffset;
				pic2topy = actionY+ ytoffset;
				pic2bottomx = actionX+ xboffset;
				pic2bottomy = actionY+ yboffset;
				invalidate();}	
			else if(is3){
				pic3topx = actionX+ xtoffset;
				pic3topy = actionY+ ytoffset;
				pic3bottomx = actionX+ xboffset;
				pic3bottomy = actionY+ yboffset;
				invalidate();}	
		}
		if(isPressed == true && eventAction ==1){
			is1= is2 = is3 = isPressed = false;
			invalidate();
		}
		return true;
	}
	
	public int getAnswer(){
		return answerInt;
	}
	
	public void setAnswer(int prevAnswer){
		//TODO: Implement this please! This should make allow the user to return to this question,
		//and have the answer they gave still be selected
	}
}

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
	//Initial positions of all the shapes
	private RectF square = new RectF(590, 300, 790, 500);
	private int pic1topx = 205;
	private int pic2topx = 495;
	private int pic3topx = 985;
	private int pic1topy = 305;
	private int pic2topy = 50;
	private int pic3topy = 150;
	private int height = 154;
	private int width = 200;
	private int answerInt=-1;
	private int xtoffset = 0;
	private int ytoffset = 0;
	private boolean is1, is2, is3, isPressed, isAnimation = false;
	private Bitmap bit1, bit2, bit3, setUp;

	public DragQuestionView(Context c) {
		super(c);
		setUpDrag();
	}

	public DragQuestionView(Context c, AttributeSet a) {
		super(c, a);
		setUpDrag();
	}
	
	private void setUpDrag(){
		height = 154;
		width = 200;
		bit1 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.happypup);
		bit2 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.neutralpup);
		bit3 = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.verysadpup);
		
		bit1 = setUp.createScaledBitmap(bit1, width, height, false);
		bit2 = setUp.createScaledBitmap(bit2, width, height, false);
		bit3 = setUp.createScaledBitmap(bit3, width, height, false);
	}

	/**
	 * Draw the canvas as a soft teal. Check if any of the images are
	 * intersecting the answer box. If they are, change the stored answer to the
	 * assigned int value of the image. Redraw the pictures.
	 * 
	 * @param- The canvas to draw
	 */
	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(153, 255, 255);
		int colorInt = Color.rgb(51, 204, 204);
		Paint paint = new Paint();
		paint.setColor(colorInt);
		if (!isAnimation && !square.intersects(pic1topx, pic1topy, pic1topx+width, pic1topy+height)
				&& !square.intersects(pic2topx, pic2topy, pic2topx+width, pic2topy+height)
				&& !square.intersects(pic3topx, pic3topy, pic3topx+width, pic3topy+height)) {
			answerInt = -1;
		} else if (!isAnimation) {
			// Checks intersection with each picture. Note: cant have if-else
			// structure here because that
			// means pic1 will never get kicked out.
			if (square.intersects(pic1topx, pic1topy, pic1topx+width, pic1topy+height)) {
				if (answerInt != 10) {
					clearSquare();
					answerInt = 10;
				}
			}
			if (square.intersects(pic2topx, pic2topy, pic2topx+width, pic2topy+height)) {
				if (answerInt != 6) {
					clearSquare();
					answerInt = 6;
				}
			}
			if (square.intersects(pic3topx, pic3topy, pic3topx+width, pic3topy+height)) {
				if (answerInt != 2) {
					clearSquare();
					answerInt = 2;
				}
			}
		}

		// Update the x and y coordinates of each image
		square.set(590, 300, 790, 500);
		canvas.drawRect(square, paint);
		canvas.drawBitmap(bit1, pic1topx, pic1topy, null);
		canvas.drawBitmap(bit2, pic2topx, pic2topy, null);
		canvas.drawBitmap(bit3, pic3topx, pic3topy, null);
	}

	/**
	 * Detects which image is currently in box using answerInt, and throws that image out 
	 * of the box
	 */
	private void clearSquare() {
		if (answerInt == 0) {
			return;
		} 
		else if (answerInt == 6) {
			pic2topx = 495;
			pic2topy = 50;
		} 
		else if (answerInt == 2) {
			pic3topx = 985;
			pic3topy = 150;
		} 
		else if (answerInt == 10) {
			pic1topx = 205;
			pic1topy = 305;
		}
	}

	/**
	 * This method makes it so the user can drag each image if its being
	 * touched.
	 * 
	 * @param- The motion event being queried
	 * @return- always true
	 */

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int eventAction = event.getAction();
		int actionX = (int) event.getX(event.findPointerIndex(event
				.getActionIndex()));
		int actionY = (int) event.getY(event.findPointerIndex(event
				.getActionIndex()));
		// Find the offset between the edges and the spot its touched, so that
		// each image is consistently dragged from the spot it was touched.
		if (eventAction == 0) {
			if (actionX < pic1topx+width && actionY < pic1topy+height
					&& actionX > pic1topx && actionY > pic1topy) {
				xtoffset = pic1topx - actionX;
				ytoffset = pic1topy - actionY;
				is1 = true;
				invalidate();
			} else if (actionX < pic2topx+width && actionY < pic2topy+height
					&& actionX > pic2topx && actionY > pic2topy) {
				xtoffset = pic2topx - actionX;
				ytoffset = pic2topy - actionY;
				is2 = true;
				invalidate();
			} else if (actionX < pic3topx+width && actionY < pic3topy+height
					&& actionX > pic3topx && actionY > pic3topy) {
				xtoffset = pic3topx - actionX;
				ytoffset = pic3topy - actionY;
				is3 = true;
				invalidate();
			}
			isPressed = true;
		}
		// Apply the offset to the x and y values of the point of contact, and
		// change the x and y coordinates of the touched image
		if (event.getPressure() != 0 && isPressed) {
			if (is1) {
				pic1topx = actionX + xtoffset;
				pic1topy = actionY + ytoffset;
				invalidate();
			} else if (is2) {
				pic2topx = actionX + xtoffset;
				pic2topy = actionY + ytoffset;
				invalidate();
			} else if (is3) {
				pic3topx = actionX + xtoffset;
				pic3topy = actionY + ytoffset;
				invalidate();
			}
		}
		if (isPressed == true && eventAction == 1) {
			is1 = is2 = is3 = isPressed = false;
			invalidate();
		}
		return true;
	}

	public int getAnswer() {
		return answerInt;
	}

	/**
	 * Reads the previous answer, and moves the corresponding puppy into the square
	 * @param prevAnswer- the integer representation of the previous answer given
	 */
	public void setAnswer(int prevAnswer){
		if(prevAnswer==-1){
			return;
		} 
		else if (prevAnswer == 2) {
			pic3topx = (int) square.left;
			pic3topy = (int) square.top;
			answerInt = prevAnswer;
		} 
		else if (prevAnswer == 6) {
			pic2topx = (int) square.left;
			pic2topy = (int) square.top;
			answerInt = prevAnswer;
		}
		 else if (prevAnswer == 10) {
			pic1topx = (int) square.left;
			pic1topy = (int) square.top;
			answerInt = prevAnswer;
		}
		invalidate();
	}

	/**
	 * Increases the size of the answer in the box, as a part of the animation
	 * 
	 * @param The- image to be blown up in size
	 */
	private void grow(int num) {

		if (num == 1) {
				pic1topy = pic1topy-200;
				pic1topx = pic1topx - 150;
				bit1 = setUp.createScaledBitmap(bit1, 400, 308, false);
		} else if (num == 2) {
				pic2topy = pic2topy - 200;
				pic2topx = pic2topx - 150;
				bit2 = setUp.createScaledBitmap(bit2, 400, 308, false);
		} else if (num == 3) {
				pic3topy = pic3topy - 200;
				pic3topx = pic3topx - 150;
				bit3 = setUp.createScaledBitmap(bit3, 400, 308, false);
		}

	}

	/**
	 * Move the image indicated by num by the given increment
	 * 
	 * @param num- the image to be moved
	 * @param increment- the increment to move the image by
	 */
	private void move(int num) {
		if (num == 1) {
			pic1topx = -220;
			pic1topy = -220;
		} else if (num == 2) {
			pic2topx = -220;
			pic2topy = -220;
		} else if (num == 3) {
			pic3topx = -220;
			pic3topy = -220;
		}
	}
	/**
	 * Moves all unselected images off the canvas, and blows up the selected image
	 * 
	 */
	public void animate() {
		isAnimation = true;
		if (answerInt == 10) {
			setAnswer(10);
			move(2);
			move(3);
			grow(1);
		}
		if (answerInt == 6) {
			setAnswer(6);
			move(3);
			move(1);
			grow(2);
		}
		if (answerInt == 2) {
			setAnswer(2);
			move(1);
			move(2);
			grow(3);
		}
		invalidate();
		isAnimation = false;
		
	}

}

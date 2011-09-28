package px.robot.util;

public class RGB {
	private int color;
	
	public RGB(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getR(){
		return (color >> 16) & 0xff;
	}
	public int getG(){
		return (color >> 8) & 0xff;
	}
	public int getB(){
		return color & 0xff;
	}
}

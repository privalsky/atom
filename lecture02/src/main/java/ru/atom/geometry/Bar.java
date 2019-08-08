package ru.atom.geometry;

public class Bar implements Collider{

	private final Point topLeft;
	private final int height;
	private final int width;
	
	
	public Bar(Point point1, Point point2) {
		
		topLeft = new Point(Math.min(point1.getX(), point2.getX()),Math.max(point1.getY(), point2.getY()));
		width = Math.abs(point1.getX() - point2.getX());
        height = Math.abs(point1.getY() - point2.getY());
	}
		
	
	
	
	@Override
	public boolean isColliding(Collider other) {
	
		if(other instanceof Point) return contains((Point)other);
		if(other instanceof Bar) return intersects((Bar)other);
		
		return false;
	}

	
	private boolean intersects(Bar otherBar) {
		
	    Point thisCenter = getCenter(this);
        Point otherCenter = getCenter(otherBar);
        int xDist = Math.abs(thisCenter.getX() - otherCenter.getX());
        int yDist = Math.abs(thisCenter.getY() - otherCenter.getY());
        return xDist <= (width + otherBar.width) / 2 && yDist <= (height + otherBar.height) / 2;
        
		
	}


	private static Point getCenter(Bar bar) {
        int x = bar.topLeft.getX() + bar.width / 2;
        int y = bar.topLeft.getY() - bar.height / 2;
        return new Point(x, y);
    }

	private boolean contains(Point point) {
		 return point.getX() >= topLeft.getX()
				&& point.getY() <= topLeft.getY()
				&& point.getX() <= (topLeft.getX() + width)
				&& point.getY() >= (topLeft.getY() - height);

	}




	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bar)) {
            return false;
        }
        Bar otherBar = (Bar) o;
        return topLeft.equals(otherBar.topLeft) 
        		&& width == otherBar.width 
        		&& height == otherBar.height;
    }
	
	
}

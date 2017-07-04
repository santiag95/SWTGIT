package org.iMage.geometrify;




public class RectanglePictureFilter extends GeneralPictureFilter{

	
	public RectanglePictureFilter(IPointGenerator pointGenerator) {
		super(pointGenerator);
	}
	

	@Override
	protected IPrimitive generatePrimitive() {
		// TODO Auto-generated method stub
		return new Rectangle(pointGenerator.nextPoint(), pointGenerator.nextPoint());
	}

}

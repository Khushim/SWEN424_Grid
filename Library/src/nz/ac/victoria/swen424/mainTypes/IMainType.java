package nz.ac.victoria.swen424.mainTypes;

import java.util.List;

/**
 * Interface for all MainTypes to extend, helps with display  
 * for graphical representation of the simulations
 * */
public interface IMainType {

	/**
	 * Should return the data stored within a singular object
	 * eg A Transformer stores: _name, _maxcapacity, _efficiency 
	 * @return 
	 * */
	public ElTransformer getLeftTransformer();
	public ElTransformer getRightTransformer();
	public List <MainBaseType> getProdCon();
	public void setRender(int x, int y, int size);
 
}
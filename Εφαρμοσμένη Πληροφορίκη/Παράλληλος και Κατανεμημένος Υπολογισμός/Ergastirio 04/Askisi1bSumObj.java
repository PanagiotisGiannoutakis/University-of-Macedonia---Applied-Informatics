class Askisi1bSumObj {

    double mySum;
    
    public Askisi1bSumObj (){
		this.mySum = 0;
    }

    public synchronized void add (int myID, double step){
		double x = ((double)myID+0.5)*step;
		mySum += 4.0/(1.0+x*x);
    }
	
	public double getSum() {
		return mySum;
	}
}
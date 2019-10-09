package com.useridentify.algorithm;

import android.util.Log;



public class Winnow {
	static final double DEFAULT_ALPHA = 2.0; 
	static final double DEFAULT_BETA = 2.0;   
	static final double DEFAULT_NFACTOR = .5;
	
	/**
	 *constant to multiply to "correct" weights in promotion step
	 */
	double alpha;
	/**
	 *constant to divide "incorrect" weights by in demotion step
	 */
	double beta;
	/**
	 *threshold for sum of wi*xi in formulating guess 
	 */
	double theta;
	/** 
	 *factor of n to set theta to. e.g. if n=1/2, theta = n/2.
	 */
	double nfactor;
	/**
	 *array of weights, one for each feature, initialized to 1
	 */
	double [][] weights;
	Lables lab;
	public Winnow(Lables lab){
		alpha = DEFAULT_ALPHA;
		beta = DEFAULT_BETA; 
		nfactor = DEFAULT_NFACTOR;
		this.lab =lab; 
	}
	public void train (Dataset trainingList)
	{
		int numLabels = lab.size();
		int numFeats = trainingList.cowsize(); 
		this.theta =  numFeats * this.nfactor;
		this.weights = new double [numLabels][numFeats];
		// init weights to 1
		for(int i=0; i<numLabels; i++)
			for(int j=0; j<numFeats; j++)
				this.weights[i][j] = 1.0;
		//System.out.println("Init weights to 1.  Theta= "+theta);
		// loop through all instances
		for (int ii = 0; ii < trainingList.rowsize(); ii++){
			Instance inst =  trainingList.get(ii);
			String label= inst.getLable();
			double fv[] = inst.getData();
			double[] results = new double [numLabels]; 
			int fvisize = fv.length;
			
			int correctIndex = lab.getIndex(label);
			
			for(int rpos=0; rpos < numLabels; rpos++)
		    results[rpos]=0;
			// sum up xi*wi for each class
			for(int fvi=0; fvi < fvisize; fvi++){
				//System.out.println("feature index "+fi);
				for(int lpos=0; lpos < numLabels; lpos++)
			    results[lpos] += this.weights[lpos][fvi];
			}
			for(int ri=0; ri < numLabels; ri++){
				if(results[ri] > this.theta){ // guess 1
					if(correctIndex != ri) // correct is 0
				    demote(ri, inst);
				}
				else{ // guess 0
					if(correctIndex == ri) // correct is 1
					promote(ri, inst);   
				}
			}
//			System.out.println("Results guessed:")
		for(int x=0; x<numLabels; x++)
		    Log.e("results"+x+"="+results[x], "results"+x+"="+results[x]);
		 	Log.e("Correct label: "+correctIndex,"Correct label: "+correctIndex );
		 	Log.e("Weights are","Weights are");
			
		}
		for(int h=0; h<numLabels; h++){
			for(int g=0; g<numFeats; g++)
			Log.e(h+" "+g+" "+weights[h][g], h+" "+g+" "+weights[h][g]);
		}
	}
	public String classify (Instance instance){
		int numClasses = lab.size();
		Log.e("size="+numClasses, "size="+numClasses);
		double[] scores = new double[numClasses];
		Log.e("GGGGGGGAAAAAAAAAAAAAA", "GGGGGGGAAAAAAAAAAAAAA");
		double fv[] = instance.getData();;
		// Make sure the feature vector's feature dictionary matches
		// what we are expecting from our data pipe (and thus our notion
		// of feature probabilities.
		int fvisize = fv.length;
		Log.e("fvisize="+fvisize, "fvisize="+fvisize);
		
		// Set the scores by summing wi*xi
		for (int fvi = 0; fvi < fvisize; fvi++) {
			for (int ci = 0; ci < numClasses; ci++)
			{
				Log.e("LLLLLLLLLLLLLL", "LLLLLLLLLLLLLL");
				Log.e("fvi="+fvi, "ci="+ci);
				Log.e("this.weights[ci][fvi]="+this.weights[ci][fvi], "this.weights[ci][fvi]="+this.weights[ci][fvi]);
				scores[ci] += this.weights[ci][fvi];
				Log.e("SSSSSSSSSSSSSSAAAAAA", "SSSSSSSSSSSSSSAAAAAA");
			}
		    
		}
		Log.e("GGGGHHHHHWWWWWWWWAAAAAAA", "GGGGHHHHHWWWWWWWWAAAAAAA");
		int bestindex=0;
		double bestscore=-100;
		for(int i = 0;i < scores.length;i++)
		{
			if(scores[i]>bestscore)
			{
				bestscore= scores[i];
				bestindex = i;
			}	
		}
		Log.e("GGGGKKKKKKKKKKKAAAA", "GGGGKKKKKKKKKKKAAAA");
		return lab.get(bestindex);
		
		// Create and return a Classification object
		
	}		
  /**
   * Promotes (by {@link #alpha alpha}) the weights 
   * responsible for the incorrect guess
   * @param lpos index of incorrectly guessed label
   * @param fv feature vector
   */
  private void promote(int lpos, Instance fv){
		int fvisize = fv.size();
	  // learner predicted 0, correct is 1 -> promotion
		for(int fvi=0; fvi < fvisize; fvi++){
			this.weights[lpos][fvi] *= this.alpha;
		}		
	}

  /**
   *Demotes (by {@link #beta beta) the weights 
   * responsible for the incorrect guess
   * @param lpos index of incorrectly guessed label
   * @param fv feature vector
   */
  private void demote(int lpos, Instance fv){
	  	int fvisize = fv.size();
		// learner predicted 1, correct is 0 -> demotion
		for(int fvi=0; fvi < fvisize; fvi++){
			this.weights[lpos][fvi] /= this.beta;
		}		
	}

}

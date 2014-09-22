package lsdr.user.based.recommender.intro.trivial;
//
//Copyright (c) 1979, the Gra projects.
//Please see the AUTHORS file for details.
//All rights reserved.
//Use of this source code is governed by a MIT-style license
//that can be found in the LICENSE file.
//
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
/**
	@author Gra <Gołębiewski Radosław A.>
	    https://github.com/golebier or https://golebier.github.io
	    https://google.com/+RadoslawGolebiewski
	    http://www.linkedin.com/pub/rados%C5%82aw-go%C5%82%C4%99biewski/70/832/35
*/
// TODO glue identical
class IREvaluatorIntro {
	// TODO maybe as field not constant?
	private static final int AMOUNT_OF_NEIGHBORS = 2;
	/**
	 * model read from csv file:
	 *  1-5 users
	 *  101-107 books
	 *  those could be UUIDs by title and user.
	 *  1.0 - 5.0 ratings
	 */
	private DataModel model = null;

	public IREvaluatorIntro(DataModel model) {
		this.model = model;
	}

	public IRStatistics evaluate() throws TasteException {
	    RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
	    RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
	        @Override
	        public Recommender buildRecommender(DataModel model) throws TasteException {
	        	// same as RecommenderIntro // TODO prepareRecommender?
	        	UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
	        	UserNeighborhood neighborhood = new NearestNUserNeighborhood(AMOUNT_OF_NEIGHBORS, similarity, model);

	        	return new GenericUserBasedRecommender(model, neighborhood, similarity);
	        }
	    };
	    IRStatistics stats = evaluator.evaluate(recommenderBuilder, null, model, null, 2
	    		, GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD, 1.0);
		return stats;
	}
//  System.out.println(stats.getPrecision());
//  System.out.println(stats.getRecall());
}

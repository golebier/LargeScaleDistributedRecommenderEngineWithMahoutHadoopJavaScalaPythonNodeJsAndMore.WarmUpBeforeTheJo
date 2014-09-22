package lsdr.user.based.recommender.intro.trivial;
//
//Copyright (c) 1979, the Gra projects.
//Please see the AUTHORS file for details.
//All rights reserved.
//Use of this source code is governed by a MIT-style license
//that can be found in the LICENSE file.
//
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;
/**
 * 1. plan: vii -- x; 11 -- 114;
 * 2. 15, @code & TeXDoc ...
 * @author gra
 *
 */
//
//@author Gra <Gołębiewski Radosław A.>
//  https://github.com/golebier or https://golebier.github.io
//  https://google.com/+RadoslawGolebiewski
//  http://www.linkedin.com/pub/rados%C5%82aw-go%C5%82%C4%99biewski/70/832/35
//
class EvaluatorIntro {
	// TODO maybe as field not constant?
	private static final int AMOUNT_OF_NEIGHBORS = 2;
	private DataModel model = null;

	public EvaluatorIntro(DataModel model) {
		this.model = model;
	}

	public Double evaluate() throws TasteException {
		RandomUtils.useTestSeed();

		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();

		RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
				UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
				UserNeighborhood neighborhood = new NearestNUserNeighborhood(AMOUNT_OF_NEIGHBORS, similarity, model);
        
				return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
		};
		//                                                                 70% as a training data
		double score = evaluator.evaluate(recommenderBuilder, null, model, 0.7, 1.0);

		return score;
	}

	public void setModel(DataModel model) {
		this.model = model;
	}
}

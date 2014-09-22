package lsdr.user.based.recommender.intro.trivial;
//
//Copyright (c) 1979, the Gra projects.
//Please see the AUTHORS file for details.
//All rights reserved.
//Use of this source code is governed by a MIT-style license
//that can be found in the LICENSE file.
//
import java.util.List;

import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
/**
	@author Gra <Gołębiewski Radosław A.>
	    https://github.com/golebier or https://golebier.github.io
	    https://google.com/+RadoslawGolebiewski
	    http://www.linkedin.com/pub/rados%C5%82aw-go%C5%82%C4%99biewski/70/832/35
*/
class RecommenderIntro {
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

	public RecommenderIntro(DataModel model) {
		this.model = model;
	}

	public List<RecommendedItem> recommend(int id, int amount) throws Exception {
		// TODO as fields, then only id and amount is changed
	    final UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
	    final UserNeighborhood neighborhood = new NearestNUserNeighborhood(AMOUNT_OF_NEIGHBORS, similarity, model);
	    final Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
	
	    List<RecommendedItem> recommendations = recommender.recommend(id, amount);

	    return recommendations;
	}

	public void setModel(DataModel model) {
		this.model = model;
	}
}

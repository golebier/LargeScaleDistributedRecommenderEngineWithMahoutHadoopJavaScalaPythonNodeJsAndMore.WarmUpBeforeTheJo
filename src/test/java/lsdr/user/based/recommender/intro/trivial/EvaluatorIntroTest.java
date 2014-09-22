package lsdr.user.based.recommender.intro.trivial;
//
//Copyright (c) 1979, the Gra projects.
//Please see the AUTHORS file for details.
//All rights reserved.
//Use of this source code is governed by a MIT-style license
//that can be found in the LICENSE file.
//
import java.io.File;

import junit.framework.Assert;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.testng.annotations.Test;
/**
	@author Gra <Gołębiewski Radosław A.>
	    https://github.com/golebier or https://golebier.github.io
	    https://google.com/+RadoslawGolebiewski
	    http://www.linkedin.com/pub/rados%C5%82aw-go%C5%82%C4%99biewski/70/832/35
*/
public class EvaluatorIntroTest {
	/**
	 * model read from csv file:
	 *  1-5 users, first column
	 *  101-107 books, second column
	 *  those could be UUIDs by title and user.
	 *  1.0 - 5.0 ratings, third column
	 */
	private static final String CSV_FILE_PATH
		= "src/test/resources/lsdr/user/based/recommender/intro/trivial/intro.csv";

	@Test
	public void readModelAndUseRecomender() throws Exception {
		EvaluatorIntro evaluatorIntro = new EvaluatorIntro(new FileDataModel(new File(CSV_FILE_PATH)));
		// TODO make cleanup
		Assert.assertNotNull(evaluatorIntro);

		System.out.println(evaluatorIntro.evaluate());
	}
}

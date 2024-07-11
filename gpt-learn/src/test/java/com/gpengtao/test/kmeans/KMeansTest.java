package com.gpengtao.test.kmeans;

import com.gpengtao.utils.JsonUtil;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author pengtao.geng on 2024/7/10 18:12.
 */
public class KMeansTest {

	@Test
	public void test_1() {
		String json = """
				[[854, 342, 1096, 470],
				[854, 342, 1098, 482],
				[844, 342, 1104, 472],
				[854, 342, 1104, 482],
				[846, 336, 1088, 472],
				[286, 598, 490, 718],
				[848, 338, 1108, 488],
				[850, 340, 1112, 466],
				[850, 340, 1108, 486],
				[854, 342, 1104, 468],
				[850, 340, 1100, 468],
				[158, 578, 386, 698],
				[848, 336, 1092, 466],
				[856, 342, 1102, 466],
				[846, 342, 1110, 474]]""";
		List<Double[]> boxes = JsonUtil.ofList(json, Double[].class);

		List<DoublePoint> points = boxes.stream()
				.map(box -> new DoublePoint(Arrays.stream(box).mapToDouble(Double::intValue).toArray()))
				.collect(Collectors.toList());

		KMeansPlusPlusClusterer<DoublePoint> cluster = new KMeansPlusPlusClusterer<>(2);
		List<CentroidCluster<DoublePoint>> clustered = cluster.cluster(points);
		System.out.println(clustered);
	}
}

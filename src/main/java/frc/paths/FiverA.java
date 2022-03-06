package frc.paths;

import com.team319.trajectory.Path;

public class FiverA extends Path {
   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading
	private static final double[][] points = {
				{0.0200,1.9426,10.8570,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,-0.8663},
				{0.0200,1.9478,10.8509,0.0120,0.4005,10.0235,1.1753,0.0120,0.4000,10.0000,0.0000,0.0120,0.3995,9.9765,-1.1753,-0.8663},
				{0.0200,1.9555,10.8417,0.0240,0.6016,10.0554,1.5937,0.0240,0.6000,10.0000,0.0000,0.0240,0.5984,9.9446,-1.5937,-0.8663},
				{0.0200,1.9659,10.8295,0.0401,0.8037,10.1066,2.5619,0.0400,0.8000,10.0000,0.0000,0.0399,0.7963,9.8934,-2.5619,-0.8664},
				{0.0200,1.9789,10.8143,0.0603,1.0072,10.1731,3.3231,0.0600,1.0000,10.0000,0.0000,0.0597,0.9928,9.8269,-3.3231,-0.8666},
				{0.0200,1.9944,10.7960,0.0845,1.2122,10.2532,4.0052,0.0840,1.2000,10.0000,0.0000,0.0835,1.1878,9.7468,-4.0052,-0.8668},
				{0.0200,2.0125,10.7746,0.1129,1.4191,10.3450,4.5891,0.1120,1.4000,10.0000,0.0000,0.1111,1.3809,9.6550,-4.5891,-0.8672},
				{0.0200,2.0332,10.7502,0.1454,1.6281,10.4461,5.0560,0.1440,1.6000,10.0000,0.0000,0.1426,1.5719,9.5539,-5.0560,-0.8678},
				{0.0200,2.0565,10.7228,0.1822,1.8391,10.5538,5.3881,0.1800,1.8000,10.0000,0.0000,0.1778,1.7609,9.4461,-5.3882,-0.8685},
				{0.0200,2.0823,10.6922,0.2233,2.0524,10.6652,5.5689,0.2200,2.0000,10.0000,0.0000,0.2167,1.9476,9.3348,-5.5690,-0.8696},
				{0.0200,2.1107,10.6586,0.2686,2.2680,10.7769,5.5831,0.2640,2.2000,10.0000,0.0000,0.2594,2.1320,9.2231,-5.5832,-0.8709},
				{0.0200,2.1415,10.6218,0.3184,2.4857,10.8852,5.4174,0.3120,2.4000,10.0000,0.0000,0.3056,2.3143,9.1148,-5.4177,-0.8727},
				{0.0200,2.1749,10.5820,0.3725,2.7054,10.9865,5.0611,0.3640,2.6000,10.0000,0.0000,0.3555,2.4946,9.0135,-5.0614,-0.8748},
				{0.0200,2.2108,10.5389,0.4310,2.9269,11.0766,4.5061,0.4200,2.8000,10.0000,0.0000,0.4090,2.6731,8.9234,-4.5065,-0.8773},
				{0.0200,2.2491,10.4927,0.4940,3.1500,11.1515,3.7479,0.4800,3.0000,10.0000,0.0000,0.4660,2.8500,8.8484,-3.7484,-0.8803},
				{0.0200,2.2897,10.4433,0.5615,3.3741,11.2072,2.7856,0.5440,3.2000,10.0000,0.0000,0.5265,3.0259,8.7927,-2.7862,-0.8838},
				{0.0200,2.3328,10.3907,0.6335,3.5989,11.2397,1.6229,0.6120,3.4000,10.0000,0.0000,0.5905,3.2011,8.7602,-1.6235,-0.8878},
				{0.0200,2.3781,10.3347,0.7099,3.8238,11.2451,0.2683,0.6840,3.6000,10.0000,0.0000,0.6581,3.3762,8.7549,-0.2690,-0.8922},
				{0.0200,2.4256,10.2755,0.7909,4.0482,11.2198,-1.2647,0.7600,3.8000,10.0000,0.0000,0.7291,3.5518,8.7801,1.2641,-0.8972},
				{0.0200,2.4754,10.2128,0.8763,4.2714,11.1606,-2.9574,0.8400,4.0000,10.0000,0.0000,0.8037,3.7286,8.8393,2.9568,-0.9026},
				{0.0200,2.5272,10.1467,0.9662,4.4927,11.0649,-4.7859,0.9240,4.2000,10.0000,0.0000,0.8818,3.9073,8.9350,4.7855,-0.9085},
				{0.0200,2.5811,10.0771,1.0604,4.7113,10.9305,-6.7220,1.0120,4.4000,10.0000,0.0000,0.9636,4.0887,9.0694,6.7219,-0.9147},
				{0.0200,2.6370,10.0041,1.1589,4.9264,10.7558,-8.7339,1.1040,4.6000,10.0000,0.0000,1.0491,4.2735,9.2441,8.7341,-0.9212},
				{0.0200,2.6948,9.9274,1.2617,5.1372,10.5400,-10.7877,1.2000,4.8000,10.0000,0.0000,1.1383,4.4627,9.4599,10.7884,-0.9280},
				{0.0200,2.7545,9.8472,1.3685,5.3429,10.2830,-12.8498,1.3000,5.0000,10.0000,0.0000,1.2315,4.6571,9.7169,12.8509,-0.9348},
				{0.0200,2.8160,9.7633,1.4794,5.5426,9.9853,-14.8885,1.4040,5.2000,10.0000,0.0000,1.3286,4.8574,10.0147,14.8901,-0.9417},
				{0.0200,2.8792,9.6757,1.5941,5.7356,9.6477,-16.8775,1.5120,5.4000,10.0000,0.0000,1.4299,5.0644,10.3523,16.8795,-0.9484},
				{0.0200,2.9442,9.5845,1.7125,5.9210,9.2718,-18.7982,1.6240,5.6000,10.0000,0.0000,1.5355,5.2790,10.7283,18.8006,-0.9548},
				{0.0200,3.0109,9.4896,1.8345,6.0982,8.8589,-20.6421,1.7400,5.8000,10.0000,0.0000,1.6455,5.5018,11.1412,20.6446,-0.9608},
				{0.0200,3.0794,9.3911,1.9598,6.2664,8.4107,-22.4126,1.8600,6.0000,10.0000,0.0000,1.7602,5.7336,11.5895,22.4150,-0.9661},
				{0.0200,3.1497,9.2889,2.0883,6.4250,7.9281,-24.1262,1.9840,6.2000,10.0000,0.0000,1.8797,5.9750,12.0720,24.1280,-0.9706},
				{0.0200,3.2218,9.1832,2.2198,6.5732,7.4119,-25.8114,2.1120,6.4000,10.0000,0.0000,2.0042,6.2268,12.5883,25.8122,-0.9741},
				{0.0200,3.2958,9.0739,2.3540,6.7104,6.8618,-27.5072,2.2440,6.6000,10.0000,0.0000,2.1340,6.4896,13.1384,27.5064,-0.9763},
				{0.0200,3.3719,8.9612,2.4907,6.8360,6.2766,-29.2590,2.3800,6.8000,10.0000,0.0000,2.2693,6.7640,13.7235,29.2557,-0.9770},
				{0.0200,3.4503,8.8452,2.6297,6.9490,5.6544,-31.1119,2.5200,7.0000,10.0000,0.0000,2.4103,7.0510,14.3456,31.1052,-0.9760},
				{0.0200,3.5312,8.7260,2.7707,7.0489,4.9923,-33.1012,2.6640,7.2000,10.0000,0.0000,2.5573,7.3511,15.0074,33.0898,-0.9730},
				{0.0200,3.6148,8.6039,2.9134,7.1346,4.2876,-35.2384,2.8120,7.4000,10.0000,0.0000,2.7106,7.6653,15.7118,35.2209,-0.9677},
				{0.0200,3.7014,8.4790,3.0575,7.2054,3.5377,-37.4907,2.9640,7.6000,10.0000,0.0000,2.8705,7.9946,16.4611,37.4652,-0.9598},
				{0.0200,3.7916,8.3517,3.2027,7.2603,2.7427,-39.7529,3.1200,7.8000,10.0000,0.0000,3.0373,8.3397,17.2555,39.7173,-0.9490},
				{0.0200,3.8857,8.2223,3.3486,7.2984,1.9065,-41.8074,3.2800,8.0000,10.0000,0.0000,3.2114,8.7015,18.0907,41.7594,-0.9349},
				{0.0200,3.9842,8.0912,3.4950,7.3192,1.0411,-43.2727,3.4440,8.2000,10.0000,0.0000,3.3930,9.0806,18.9549,43.2096,-0.9173},
				{0.0200,4.0877,7.9589,3.6415,7.3226,0.1703,-43.5399,3.6120,8.4000,10.0000,0.0000,3.5825,9.4771,19.8241,43.4595,-0.8958},
				{0.0200,4.1969,7.8260,3.7877,7.3093,-0.6639,-41.7091,3.7840,8.6000,10.0000,0.0000,3.7803,9.8902,20.6563,41.6102,-0.8700},
				{0.0200,4.3123,7.6932,3.9333,7.2814,-1.3949,-36.5508,3.9600,8.8000,10.0000,0.0000,3.9867,10.3179,21.3850,36.4349,-0.8396},
				{0.0200,4.4348,7.5613,4.0781,7.2429,-1.9258,-26.5453,4.1400,9.0000,10.0000,0.0000,4.2018,10.7562,21.9133,26.4182,-0.8045},
				{0.0200,4.5650,7.4312,4.2222,7.2004,-2.1275,-10.0824,4.3240,9.2000,10.0000,0.0000,4.4258,11.1984,22.1125,9.9567,-0.7645},
				{0.0200,4.7035,7.3041,4.3654,7.1635,-1.8458,14.0857,4.5120,9.4000,10.0000,0.0000,4.6585,11.6350,21.8287,-14.1886,-0.7198},
				{0.0200,4.8509,7.1811,4.5083,7.1450,-0.9237,46.1011,4.7040,9.6000,10.0000,0.0000,4.8995,12.0531,20.9057,-46.1517,-0.6707},
				{0.0200,5.0076,7.0634,4.6515,7.1602,0.7586,84.1181,4.9000,9.8000,10.0000,0.0000,5.1483,12.4376,19.2240,-84.0843,-0.6179},
				{0.0200,5.1737,6.9521,4.7960,7.2248,3.2333,123.7312,5.1000,10.0000,10.0000,0.0000,5.4037,12.7726,16.7522,-123.5885,-0.5624},
				{0.0200,5.3458,6.8502,4.9402,7.2084,-0.8208,-202.7014,5.3000,10.0000,10.0000,0.0000,5.6595,12.7890,0.8192,-796.6477,-0.5066},
				{0.0200,5.5233,6.7580,5.0854,7.2592,2.5420,168.1402,5.5000,10.0000,10.0000,0.0000,5.9143,12.7383,-2.5373,-167.8294,-0.4518},
				{0.0200,5.7054,6.6755,5.2328,7.3723,5.6553,155.6612,5.7000,10.0000,10.0000,0.0000,6.1668,12.6253,-5.6451,-155.3894,-0.3993},
				{0.0200,5.8915,6.6024,5.3836,7.5381,8.2883,131.6520,5.9000,10.0000,10.0000,0.0000,6.4160,12.4599,-8.2742,-131.4549,-0.3501},
				{0.0200,6.0809,6.5381,5.5385,7.7446,10.3260,101.8837,6.1000,10.0000,10.0000,0.0000,6.6610,12.2537,-10.3097,-101.7750,-0.3050},
				{0.0200,6.2729,6.4820,5.6981,7.9801,11.7726,72.3311,6.3000,10.0000,10.0000,0.0000,6.9014,12.0186,-11.7558,-72.3043,-0.2646},
				{0.0200,6.4668,6.4332,5.8628,8.2346,12.7239,47.5652,6.5000,10.0000,10.0000,0.0000,7.1367,11.7644,-12.7078,-47.6015,-0.2293},
				{0.0200,6.6623,6.3908,6.0328,8.5011,13.3275,30.1807,6.7000,10.0000,10.0000,0.0000,7.3667,11.4981,-13.3130,-30.2584,-0.1993},
				{0.0200,6.8588,6.3537,6.2083,8.7761,13.7496,21.1048,6.9000,10.0000,10.0000,0.0000,7.5911,11.2234,-13.7371,-21.2060,-0.1748},
				{0.0200,7.0561,6.3209,6.3895,9.0592,14.1560,20.3196,7.1000,10.0000,10.0000,0.0000,7.8099,10.9405,-14.1458,-20.4328,-0.1560},
				{0.0200,7.2538,6.2912,6.5766,9.3534,14.7082,27.6085,7.3000,10.0000,10.0000,0.0000,8.0229,10.6465,-14.7004,-27.7301,-0.1431},
				{0.0200,7.4519,6.2634,6.7699,9.6648,15.5715,43.1687,7.5000,10.0000,10.0000,0.0000,8.2296,10.3351,-15.5664,-43.3030,-0.1364},
				{0.0200,7.6500,6.2359,6.9827,10.6427,48.8962,1666.2328,7.7000,10.0000,10.0000,0.0000,8.4167,9.3571,-48.9012,-1666.7373,-0.1492},
				{0.0200,7.8429,6.2013,7.2355,12.6407,99.8975,2550.0671,7.8960,9.8000,-10.0000,0.0000,8.5558,6.9565,-120.0302,-3556.4522,-0.2061},
				{0.0200,8.0302,6.1590,7.4489,10.6683,-98.6206,-9925.9084,8.0880,9.6000,-10.0000,0.0000,8.7265,8.5313,78.7381,9938.4160,-0.2275},
				{0.0200,8.2142,6.1205,7.5779,6.4516,-210.8340,-5610.6701,8.2760,9.4000,-10.0000,0.0000,8.9734,12.3457,190.7194,5599.0680,-0.1685},
				{0.0200,8.3971,6.1026,7.6038,1.2920,-257.9802,-2357.3107,8.4600,9.2000,-10.0000,0.0000,9.3152,17.0906,237.2460,2326.3274,-0.0103},
				{0.0200,8.5757,6.1210,7.6575,2.6884,69.8223,16390.1252,8.6400,9.0000,-10.0000,0.0000,9.7281,20.6465,177.7971,-2972.4444,0.2235},
				{0.0200,8.7414,6.1793,7.7020,2.2247,-23.1852,-4650.3736,8.8160,8.8000,-10.0000,0.0000,10.1239,19.7881,-42.9231,-11036.0120,0.4441},
				{0.0200,8.8906,6.2647,7.7385,1.8209,-20.1899,149.7635,8.9880,8.6000,-10.0000,0.0000,10.4312,15.3666,-221.0718,-8907.4321,0.5797},
				{0.0200,9.0000,6.3400,7.8366,4.9082,154.3634,8727.6680,9.1208,8.4000,-10.0000,0.0000,10.5987,8.3749,-349.5848,-6425.6511,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,-245.4104,-19988.6890,9.1208,8.2000,-10.0000,0.0000,10.5987,0.0000,-418.7466,-3458.0879,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,12270.5181,9.1208,8.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,20937.3278,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,7.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,7.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,7.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,7.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,7.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,6.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,6.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,6.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,6.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,6.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,5.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,5.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,5.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,5.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,5.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,4.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,4.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,4.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,4.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,4.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,3.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,3.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,3.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,3.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,3.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,2.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,2.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,2.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,2.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,2.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,1.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,1.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,1.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,1.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,1.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,0.8000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,0.6000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,0.4000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,0.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,0.0000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},
				{0.0200,9.0000,6.3400,7.8366,0.0000,0.0000,0.0000,9.1208,-0.2000,-10.0000,0.0000,10.5987,0.0000,0.0000,0.0000,0.6144},

	    };

	@Override
	public double[][] getPath() {
	    return points;
	}
}
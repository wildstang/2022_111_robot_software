package frc.paths;

import com.team319.trajectory.Path;

public class StealNew4 extends Path {
   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading
	private static final double[][] points = {
				{0.0200,15.9968,7.6699,0.0032,0.1600,8.0000,0.0000,0.0032,0.1600,8.0000,0.0000,0.0032,0.1600,8.0000,0.0000,-3.1032},
				{0.0200,15.9904,7.6696,0.0096,0.3207,8.0344,1.7175,0.0096,0.3200,8.0000,0.0000,0.0096,0.3193,7.9656,-1.7175,-3.1032},
				{0.0200,15.9808,7.6693,0.0193,0.4823,8.0808,2.3207,0.0192,0.4800,8.0000,0.0000,0.0191,0.4777,7.9192,-2.3207,-3.1032},
				{0.0200,15.9680,7.6688,0.0322,0.6454,8.1550,3.7137,0.0320,0.6400,8.0000,0.0000,0.0318,0.6346,7.8450,-3.7137,-3.1033},
				{0.0200,15.9520,7.6682,0.0484,0.8104,8.2507,4.7824,0.0480,0.8000,8.0000,0.0000,0.0476,0.7896,7.7493,-4.7824,-3.1035},
				{0.0200,15.9328,7.6674,0.0679,0.9777,8.3648,5.7067,0.0672,0.9600,8.0000,0.0000,0.0665,0.9423,7.6352,-5.7067,-3.1039},
				{0.0200,15.9105,7.6666,0.0909,1.1476,8.4939,6.4517,0.0896,1.1200,8.0000,0.0000,0.0883,1.0924,7.5061,-6.4518,-3.1044},
				{0.0200,15.8849,7.6657,0.1173,1.3203,8.6335,6.9838,0.1152,1.2800,8.0000,0.0000,0.1131,1.2397,7.3665,-6.9839,-3.1052},
				{0.0200,15.8561,7.6646,0.1472,1.4958,8.7790,7.2714,0.1440,1.4400,8.0000,0.0000,0.1408,1.3842,7.2210,-7.2715,-3.1064},
				{0.0200,15.8241,7.6635,0.1807,1.6743,8.9247,7.2860,0.1760,1.6000,8.0000,0.0000,0.1713,1.5257,7.0753,-7.2861,-3.1078},
				{0.0200,15.7889,7.6624,0.2178,1.8556,9.0647,7.0028,0.2112,1.7600,8.0000,0.0000,0.2046,1.6644,6.9353,-7.0031,-3.1098},
				{0.0200,15.7506,7.6612,0.2586,2.0395,9.1928,6.4027,0.2496,1.9200,8.0000,0.0000,0.2406,1.8005,6.8072,-6.4030,-3.1121},
				{0.0200,15.7090,7.6600,0.3031,2.2255,9.3022,5.4725,0.2912,2.0800,8.0000,0.0000,0.2793,1.9345,6.6977,-5.4729,-3.1151},
				{0.0200,15.6642,7.6589,0.3514,2.4133,9.3864,4.2069,0.3360,2.2400,8.0000,0.0000,0.3206,2.0667,6.6136,-4.2074,-3.1185},
				{0.0200,15.6162,7.6579,0.4034,2.6020,9.4386,2.6094,0.3840,2.4000,8.0000,0.0000,0.3646,2.1980,6.5614,-2.6100,-3.1226},
				{0.0200,15.5650,7.6570,0.4592,2.7911,9.4524,0.6932,0.4352,2.5600,8.0000,0.0000,0.4112,2.3289,6.5475,-0.6939,-3.1272},
				{0.0200,15.5106,7.6564,0.5188,2.9795,9.4221,-1.5179,0.4896,2.7200,8.0000,0.0000,0.4604,2.4605,6.5779,1.5173,-3.1324},
				{0.0200,15.4530,7.6560,0.5821,3.1664,9.3423,-3.9900,0.5472,2.8800,8.0000,0.0000,0.5123,2.5936,6.6576,3.9895,-3.1381},
				{0.0200,15.3922,7.6560,0.6492,3.3505,9.2087,-6.6789,0.6080,3.0400,8.0000,0.0000,0.5668,2.7295,6.7912,6.6786,-3.1443},
				{0.0200,15.3282,7.6564,0.7198,3.5309,9.0180,-9.5325,0.6720,3.2000,8.0000,0.0000,0.6242,2.8691,6.9819,9.5325,-3.1509},
				{0.0200,15.2610,7.6573,0.7939,3.7063,8.7682,-12.4928,0.7392,3.3600,8.0000,0.0000,0.6845,3.0137,7.2317,12.4932,-3.1579},
				{0.0200,15.1906,7.6586,0.8714,3.8754,8.4582,-15.5005,0.8096,3.5200,8.0000,0.0000,0.7478,3.1646,7.5418,15.5013,-3.1650},
				{0.0200,15.1171,7.6606,0.9522,4.0372,8.0882,-18.4992,0.8832,3.6800,8.0000,0.0000,0.8142,3.3228,7.9118,18.5006,-3.1721},
				{0.0200,15.0403,7.6632,1.0360,4.1904,7.6594,-21.4420,0.9600,3.8400,8.0000,0.0000,0.8840,3.4896,8.3406,21.4440,-3.1791},
				{0.0200,14.9604,7.6665,1.1226,4.3338,7.1734,-24.2969,1.0400,4.0000,8.0000,0.0000,0.9574,3.6661,8.8266,24.2992,-3.1858},
				{0.0200,14.8773,7.6705,1.2120,4.4665,6.6324,-27.0523,1.1232,4.1600,8.0000,0.0000,1.0344,3.8535,9.3677,27.0548,-3.1919},
				{0.0200,14.7910,7.6750,1.3037,4.5873,6.0379,-29.7220,1.2096,4.3200,8.0000,0.0000,1.1155,4.0527,9.9622,29.7242,-3.1973},
				{0.0200,14.7015,7.6802,1.3976,4.6951,5.3910,-32.3468,1.2992,4.4800,8.0000,0.0000,1.2008,4.2649,10.6092,32.3484,-3.2016},
				{0.0200,14.6089,7.6859,1.4934,4.7889,4.6911,-34.9949,1.3920,4.6400,8.0000,0.0000,1.2906,4.4911,11.3091,34.9952,-3.2045},
				{0.0200,14.5131,7.6921,1.5907,4.8676,3.9359,-37.7574,1.4880,4.8000,8.0000,0.0000,1.3853,4.7324,12.0642,37.7555,-3.2059},
				{0.0200,14.4141,7.6984,1.6893,4.9300,3.1211,-40.7405,1.5872,4.9600,8.0000,0.0000,1.4851,4.9900,12.8789,40.7352,-3.2053},
				{0.0200,14.3119,7.7048,1.7888,4.9748,2.2401,-44.0513,1.6896,5.1200,8.0000,0.0000,1.5904,5.2652,13.7597,44.0412,-3.2024},
				{0.0200,14.2065,7.7110,1.8889,5.0005,1.2846,-47.7748,1.7952,5.2800,8.0000,0.0000,1.7015,5.5595,14.7149,47.7580,-3.1968},
				{0.0200,14.0978,7.7165,1.9890,5.0054,0.2458,-51.9385,1.9040,5.4400,8.0000,0.0000,1.8190,5.8745,15.7531,51.9126,-3.1881},
				{0.0200,13.9859,7.7211,2.0887,4.9878,-0.8833,-56.4563,2.0160,5.6000,8.0000,0.0000,1.9433,6.2122,16.8815,56.4182,-3.1759},
				{0.0200,13.8708,7.7241,2.1876,4.9457,-2.1041,-61.0421,2.1312,5.7600,8.0000,0.0000,2.0748,6.5742,18.1013,60.9881,-3.1596},
				{0.0200,13.7524,7.7251,2.2852,4.8776,-3.4058,-65.0824,2.2496,5.9200,8.0000,0.0000,2.2140,6.9622,19.4014,65.0080,-3.1387},
				{0.0200,13.6308,7.7232,2.3808,4.7825,-4.7550,-67.4605,2.3712,6.0800,8.0000,0.0000,2.3615,7.3772,20.7486,67.3613,-3.1128},
				{0.0200,13.5061,7.7177,2.4741,4.6608,-6.0819,-66.3460,2.4960,6.2400,8.0000,0.0000,2.5179,7.8186,22.0730,66.2192,-3.0812},
				{0.0200,13.3785,7.7076,2.5644,4.5156,-7.2623,-59.0197,2.6240,6.4000,8.0000,0.0000,2.6836,8.2836,23.2504,58.8663,-3.0435},
				{0.0200,13.2483,7.6919,2.6514,4.3536,-8.1003,-41.9022,2.7552,6.5600,8.0000,0.0000,2.8589,8.7653,24.0850,41.7317,-2.9994},
				{0.0200,13.1158,7.6696,2.7352,4.1871,-8.3225,-11.1090,2.8896,6.7200,8.0000,0.0000,3.0439,9.2514,24.3039,10.9449,-2.9488},
				{0.0200,12.9815,7.6395,2.8159,4.0351,-7.6010,36.0779,3.0272,6.8800,8.0000,0.0000,3.2384,9.7230,23.5800,-36.1946,-2.8919},
				{0.0200,12.8462,7.6005,2.8943,3.9226,-5.6268,98.7082,3.1680,7.0400,8.0000,0.0000,3.4415,10.1551,21.6055,-98.7223,-2.8295},
				{0.0200,12.7108,7.5518,2.9719,3.8778,-2.2369,169.4955,3.3120,7.2000,8.0000,0.0000,3.6519,10.5195,18.2184,-169.3550,-2.7631},
				{0.0200,12.5760,7.4927,3.0504,3.9268,2.4480,234.2458,3.4592,7.3600,8.0000,0.0000,3.8677,10.7903,13.5398,-233.9318,-2.6945},
				{0.0200,12.4427,7.4231,3.1321,4.0861,7.9645,275.8258,3.6096,7.5200,8.0000,0.0000,4.0867,10.9509,8.0323,-275.3777,-2.6258},
				{0.0200,12.3117,7.3429,3.2193,4.3583,13.6079,282.1670,3.7632,7.6800,8.0000,0.0000,4.3067,10.9989,2.3987,-281.6781,-2.5594},
				{0.0200,12.1836,7.2526,3.3139,4.7317,18.6725,253.2324,3.9200,7.8400,8.0000,0.0000,4.5256,10.9458,-2.6575,-252.8104,-2.4972},
				{0.0200,12.0585,7.1528,3.4177,5.1857,22.6977,201.2584,4.0800,8.0000,8.0000,0.0000,4.7418,10.8122,-6.6770,-200.9767,-2.4410},
				{0.0200,11.9364,7.0445,3.5316,5.6973,25.5810,144.1658,4.2432,8.1600,8.0000,0.0000,4.9543,10.6211,-9.5579,-144.0423,-2.3917},
				{0.0200,11.8171,6.9285,3.6566,6.2480,27.5361,97.7558,4.4096,8.3200,8.0000,0.0000,5.1621,10.3908,-11.5132,-97.7651,-2.3503},
				{0.0200,11.7000,6.8058,3.7931,6.8275,28.9746,71.9213,4.5792,8.4800,8.0000,0.0000,5.3647,10.1317,-12.9536,-72.0225,-2.3172},
				{0.0200,11.5843,6.6775,3.9418,7.4357,30.4081,71.6778,4.7520,8.6400,8.0000,0.0000,5.5616,9.8439,-14.3904,-71.8394,-2.2932},
				{0.0200,11.4691,6.5445,4.1035,8.0842,32.4270,100.9454,4.9280,8.8000,8.0000,0.0000,5.7519,9.5156,-16.4137,-101.1622,-2.2788},
				{0.0200,11.3529,6.4081,4.2794,8.7964,35.6099,159.1415,5.1072,8.9600,8.0000,0.0000,5.9344,9.1236,-19.6027,-159.4534,-2.2756},
				{0.0200,11.2343,6.2695,4.4690,9.4770,34.0283,-79.0762,5.2896,9.1200,8.0000,0.0000,6.1096,8.7630,-18.0299,78.6445,-2.2827},
				{0.0200,11.1121,6.1298,4.6699,10.0453,28.4154,-280.6452,5.4752,9.2800,8.0000,0.0000,6.2799,8.5145,-12.4226,280.3651,-2.2980},
				{0.0200,10.9851,5.9901,4.8801,10.5097,23.2196,-259.7929,5.6640,9.4400,8.0000,0.0000,6.4473,8.3700,-7.2285,259.7018,-2.3194},
				{0.0200,10.8527,5.8511,5.0974,10.8674,17.8889,-266.5337,5.8560,9.6000,8.0000,0.0000,6.6140,8.3320,-1.8966,266.5960,-2.3448},
				{0.0200,10.7144,5.7133,5.3198,11.1209,12.6750,-260.6969,6.0512,9.7600,8.0000,0.0000,6.7819,8.3985,3.3206,260.8610,-2.3720},
				{0.0200,10.5747,5.5816,5.5381,10.9147,-10.3102,-1149.2556,6.2432,9.6000,-8.0000,0.0000,6.9476,8.2847,-5.6874,-450.3986,-2.3983},
				{0.0200,10.4342,5.4555,5.7508,10.6354,-13.9675,-182.8656,6.4320,9.4400,-8.0000,0.0000,7.1125,8.2442,-2.0273,183.0006,-2.4222},
				{0.0200,10.2933,5.3347,5.9570,10.3055,-16.4942,-126.3364,6.6176,9.2800,-8.0000,0.0000,7.2776,8.2542,0.5004,126.3883,-2.4427},
				{0.0200,10.1526,5.2186,6.1558,9.9427,-18.1405,-82.3128,6.8000,9.1200,-8.0000,0.0000,7.4435,8.2971,2.1464,82.3006,-2.4592},
				{0.0200,10.0129,5.1064,6.3470,9.5596,-19.1528,-50.6156,6.9792,8.9600,-8.0000,0.0000,7.6107,8.3603,3.1577,50.5624,-2.4712},
				{0.0200,9.8745,4.9976,6.5303,9.1647,-19.7445,-29.5877,7.1552,8.8000,-8.0000,0.0000,7.7795,8.4352,3.7479,29.5126,-2.4785},
				{0.0200,9.7382,4.8915,6.7056,8.7631,-20.0829,-16.9208,7.3280,8.6400,-8.0000,0.0000,7.9498,8.5169,4.0847,16.8364,-2.4809},
				{0.0200,9.6043,4.7873,6.8727,8.3573,-20.2879,-10.2490,7.4976,8.4800,-8.0000,0.0000,8.1218,8.6027,4.2879,10.1626,-2.4785},
				{0.0200,9.4735,4.6844,7.0317,7.9486,-20.4368,-7.4453,7.6640,8.3200,-8.0000,0.0000,8.2957,8.6914,4.4351,7.3600,-2.4710},
				{0.0200,9.3463,4.5823,7.1824,7.5372,-20.5706,-6.6911,7.8272,8.1600,-8.0000,0.0000,8.4713,8.7827,4.5673,6.6081,-2.4586},
				{0.0200,9.2230,4.4803,7.3249,7.1232,-20.6991,-6.4222,7.9872,8.0000,-8.0000,0.0000,8.6489,8.8766,4.6941,6.3416,-2.4411},
				{0.0200,9.1042,4.3779,7.4590,6.7071,-20.8038,-5.2334,8.1440,7.8400,-8.0000,0.0000,8.8283,8.9725,4.7972,5.1562,-2.4184},
				{0.0200,8.9905,4.2747,7.5848,6.2903,-20.8399,-1.8063,8.2976,7.6800,-8.0000,0.0000,9.0097,9.0692,4.8319,1.7349,-2.3906},
				{0.0200,8.8822,4.1704,7.7023,5.8756,-20.7381,5.0905,8.4480,7.5200,-8.0000,0.0000,9.1930,9.1638,4.7289,-5.1517,-2.3577},
				{0.0200,8.7799,4.0645,7.8117,5.4674,-20.4084,16.4832,8.5952,7.3600,-8.0000,0.0000,9.3780,9.2517,4.3984,-16.5270,-2.3199},
				{0.0200,8.6840,3.9571,7.9131,5.0724,-19.7499,32.9265,8.7392,7.2000,-8.0000,0.0000,9.5645,9.3265,3.7395,-32.9434,-2.2773},
				{0.0200,8.5951,3.8479,8.0071,4.6990,-18.6677,54.1107,8.8800,7.0400,-8.0000,0.0000,9.7521,9.3797,2.6577,-54.0907,-2.2305},
				{0.0200,8.5135,3.7371,8.0943,4.3571,-17.0984,78.4625,9.0176,6.8800,-8.0000,0.0000,9.9402,9.4015,1.0897,-78.3981,-2.1801},
				{0.0200,8.4395,3.6250,8.1754,4.0563,-15.0388,102.9785,9.1520,6.7200,-8.0000,0.0000,10.1278,9.3821,-0.9676,-102.8683,-2.1268},
				{0.0200,8.3734,3.5117,8.2515,3.8050,-12.5674,123.5740,9.2832,6.5600,-8.0000,0.0000,10.3141,9.3134,-3.4362,-123.4259,-2.0717},
				{0.0200,8.3150,3.3978,8.3237,3.6080,-9.8460,136.0656,9.4112,6.4000,-8.0000,0.0000,10.4979,9.1903,-6.1541,-135.8975,-2.0159},
				{0.0200,8.2645,3.2837,8.3930,3.4661,-7.0955,137.5268,9.5360,6.2400,-8.0000,0.0000,10.6781,9.0123,-8.9014,-137.3623,-1.9604},
				{0.0200,8.2213,3.1700,8.4605,3.3752,-4.5478,127.3852,9.6576,6.0800,-8.0000,0.0000,10.8538,8.7833,-11.4463,-127.2470,-1.9063},
				{0.0200,8.1853,3.0572,8.5270,3.3273,-2.3949,107.6470,9.7760,5.9200,-8.0000,0.0000,11.0240,8.5114,-13.5973,-107.5507,-1.8545},
				{0.0200,8.1558,2.9459,8.5933,3.3122,-0.7526,82.1125,9.8912,5.7600,-8.0000,0.0000,11.1881,8.2066,-15.2386,-82.0627,-1.8055},
				{0.0200,8.1322,2.8364,8.6597,3.3192,0.3483,55.0442,10.0032,5.6000,-8.0000,0.0000,11.3457,7.8798,-16.3393,-55.0362,-1.7599},
				{0.0200,8.1140,2.7292,8.7264,3.3381,0.9477,29.9734,10.1120,5.4400,-8.0000,0.0000,11.4966,7.5411,-16.9392,-29.9968,-1.7179},
				{0.0200,8.1006,2.6244,8.7936,3.3607,1.1295,9.0874,10.2176,5.2800,-8.0000,0.0000,11.6405,7.1986,-17.1218,-9.1305,-1.6795},
				{0.0200,8.0913,2.5224,8.8612,3.3806,0.9939,-6.7799,10.3200,5.1200,-8.0000,0.0000,11.7777,6.8589,-16.9873,6.7278,-1.6447},
				{0.0200,8.0855,2.4234,8.9291,3.3934,0.6380,-17.7960,10.4192,4.9600,-8.0000,0.0000,11.9082,6.5262,-16.6324,17.7424,-1.6134},
				{0.0200,8.0828,2.3275,8.9970,3.3962,0.1442,-24.6881,10.5152,4.8000,-8.0000,0.0000,12.0323,6.2034,-16.1397,24.6379,-1.5853},
				{0.0200,8.0826,2.2347,9.0648,3.3878,-0.4234,-28.3821,10.6080,4.6400,-8.0000,0.0000,12.1501,5.8920,-15.5729,28.3377,-1.5603},
				{0.0200,8.0846,2.1451,9.1321,3.3674,-1.0189,-29.7738,10.6976,4.4800,-8.0000,0.0000,12.2620,5.5924,-14.9782,29.7361,-1.5380},
				{0.0200,8.0883,2.0588,9.1988,3.3352,-1.6113,-29.6190,10.7840,4.3200,-8.0000,0.0000,12.3681,5.3047,-14.3864,29.5878,-1.5183},
				{0.0200,8.0934,1.9757,9.2647,3.2915,-2.1813,-28.5019,10.8672,4.1600,-8.0000,0.0000,12.4687,5.0283,-13.8169,28.4766,-1.5010},
				{0.0200,8.0996,1.8960,9.3294,3.2372,-2.7183,-26.8460,10.9472,4.0000,-8.0000,0.0000,12.5639,4.7627,-13.2804,26.8259,-1.4857},
				{0.0200,8.1066,1.8195,9.3929,3.1728,-3.2171,-24.9428,11.0240,3.8400,-8.0000,0.0000,12.6541,4.5071,-12.7819,24.9268,-1.4724},
				{0.0200,8.1143,1.7463,9.4549,3.0993,-3.6768,-22.9826,11.0976,3.6800,-8.0000,0.0000,12.7393,4.2607,-12.3225,22.9700,-1.4608},
				{0.0200,8.1224,1.6764,9.5152,3.0173,-4.0984,-21.0828,11.1680,3.5200,-8.0000,0.0000,12.8197,4.0226,-11.9010,21.0731,-1.4507},
				{0.0200,8.1307,1.6097,9.5738,2.9276,-4.4846,-19.3101,11.2352,3.3600,-8.0000,0.0000,12.8956,3.7923,-11.5149,19.3026,-1.4421},
				{0.0200,8.1392,1.5462,9.6304,2.8309,-4.8386,-17.6971,11.2992,3.2000,-8.0000,0.0000,12.9669,3.5691,-11.1611,17.6913,-1.4347},
				{0.0200,8.1476,1.4860,9.6849,2.7276,-5.1636,-16.2538,11.3600,3.0400,-8.0000,0.0000,13.0340,3.3524,-10.8361,16.2494,-1.4284},
				{0.0200,8.1559,1.4290,9.7373,2.6183,-5.4632,-14.9761,11.4176,2.8800,-8.0000,0.0000,13.0968,3.1417,-10.5367,14.9728,-1.4232},
				{0.0200,8.1640,1.3752,9.7874,2.5035,-5.7402,-13.8514,11.4720,2.7200,-8.0000,0.0000,13.1556,2.9365,-10.2597,13.8489,-1.4189},
				{0.0200,8.1719,1.3246,9.8350,2.3836,-5.9974,-12.8618,11.5232,2.5600,-8.0000,0.0000,13.2103,2.7364,-10.0025,12.8599,-1.4153},
				{0.0200,8.1794,1.2772,9.8802,2.2588,-6.2372,-11.9867,11.5712,2.4000,-8.0000,0.0000,13.2611,2.5412,-9.7628,11.9854,-1.4125},
				{0.0200,8.1865,1.2330,9.9228,2.1296,-6.4613,-11.2048,11.6160,2.2400,-8.0000,0.0000,13.3081,2.3504,-9.5387,11.2039,-1.4103},
				{0.0200,8.1932,1.1919,9.9627,1.9962,-6.6711,-10.4944,11.6576,2.0800,-8.0000,0.0000,13.3514,2.1638,-9.3288,10.4937,-1.4086},
				{0.0200,8.1994,1.1541,9.9999,1.8588,-6.8678,-9.8344,11.6960,1.9200,-8.0000,0.0000,13.3910,1.9812,-9.1322,9.8339,-1.4074},
				{0.0200,8.2051,1.1193,10.0343,1.7178,-7.0519,-9.2051,11.7312,1.7600,-8.0000,0.0000,13.4271,1.8022,-8.9481,9.2048,-1.4066},
				{0.0200,8.2104,1.0878,10.0657,1.5733,-7.2237,-8.5884,11.7632,1.6000,-8.0000,0.0000,13.4596,1.6267,-8.7763,8.5882,-1.4060},
				{0.0200,8.2151,1.0593,10.0942,1.4257,-7.3831,-7.9682,11.7920,1.4400,-8.0000,0.0000,13.4887,1.4543,-8.6169,7.9681,-1.4057},
				{0.0200,8.2193,1.0341,10.1197,1.2751,-7.5297,-7.3307,11.8176,1.2800,-8.0000,0.0000,13.5144,1.2849,-8.4703,7.3306,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.2074,-53.3835,-2292.6894,11.8218,1.1200,-8.0000,0.0000,13.5185,0.2076,-53.8665,-2269.8076,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.0000,-10.3701,2150.6674,11.8218,0.9600,-8.0000,0.0000,13.5185,0.0000,-10.3799,2174.3265,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.0000,0.0000,518.5057,11.8218,0.8000,-8.0000,0.0000,13.5185,0.0000,0.0000,518.9973,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.0000,0.0000,0.0000,11.8218,0.6400,-8.0000,0.0000,13.5185,0.0000,0.0000,0.0000,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.0000,0.0000,0.0000,11.8218,0.4800,-8.0000,0.0000,13.5185,0.0000,0.0000,0.0000,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.0000,0.0000,0.0000,11.8218,0.3200,-8.0000,0.0000,13.5185,0.0000,0.0000,0.0000,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.0000,0.0000,0.0000,11.8218,0.1600,-8.0000,0.0000,13.5185,0.0000,0.0000,0.0000,-1.4056},
				{0.0200,8.2200,1.0300,10.1239,0.0000,0.0000,0.0000,11.8218,-0.0000,-8.0000,0.0000,13.5185,0.0000,0.0000,0.0000,-1.4056},

	    };

	@Override
	public double[][] getPath() {
	    return points;
	}
}
package frc.TwoBallPath;

import com.team319.trajectory.Path;

public class TwoBallPath extends Path {
   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading
	private static final double[][] points = {
				{0.0200,18.2500,7.6740,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.6820,0.0120,0.4000,10.0000,0.0000,0.0120,0.4000,10.0000,0.0000,0.0120,0.4000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.6940,0.0240,0.6000,10.0000,0.0000,0.0240,0.6000,10.0000,0.0000,0.0240,0.6000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.7100,0.0400,0.8000,10.0000,0.0000,0.0400,0.8000,10.0000,0.0000,0.0400,0.8000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.7300,0.0600,1.0000,10.0000,0.0000,0.0600,1.0000,10.0000,0.0000,0.0600,1.0000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.7540,0.0840,1.2000,10.0000,-0.0000,0.0840,1.2000,10.0000,0.0000,0.0840,1.2000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,7.7820,0.1120,1.4000,10.0000,-0.0000,0.1120,1.4000,10.0000,0.0000,0.1120,1.4000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,7.8140,0.1440,1.6000,10.0000,-0.0000,0.1440,1.6000,10.0000,0.0000,0.1440,1.6000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,7.8500,0.1800,1.8000,10.0000,0.0000,0.1800,1.8000,10.0000,0.0000,0.1800,1.8000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.8900,0.2200,2.0000,10.0000,0.0000,0.2200,2.0000,10.0000,0.0000,0.2200,2.0000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.9340,0.2640,2.2000,10.0000,0.0000,0.2640,2.2000,10.0000,0.0000,0.2640,2.2000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,7.9820,0.3120,2.4000,10.0000,-0.0000,0.3120,2.4000,10.0000,0.0000,0.3120,2.4000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,8.0340,0.3640,2.6000,10.0000,0.0000,0.3640,2.6000,10.0000,0.0000,0.3640,2.6000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.0900,0.4200,2.8000,10.0000,-0.0000,0.4200,2.8000,10.0000,0.0000,0.4200,2.8000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,8.1500,0.4800,3.0000,10.0000,-0.0000,0.4800,3.0000,10.0000,0.0000,0.4800,3.0000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,8.2140,0.5440,3.2000,10.0000,0.0000,0.5440,3.2000,10.0000,0.0000,0.5440,3.2000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.2820,0.6120,3.4000,10.0000,0.0000,0.6120,3.4000,10.0000,0.0000,0.6120,3.4000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.3540,0.6840,3.6000,10.0000,0.0000,0.6840,3.6000,10.0000,0.0000,0.6840,3.6000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.4300,0.7600,3.8000,10.0000,0.0000,0.7600,3.8000,10.0000,0.0000,0.7600,3.8000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.5100,0.8400,4.0000,10.0000,0.0000,0.8400,4.0000,10.0000,0.0000,0.8400,4.0000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.5940,0.9240,4.2000,10.0000,0.0000,0.9240,4.2000,10.0000,0.0000,0.9240,4.2000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.6820,1.0120,4.4000,10.0000,-0.0000,1.0120,4.4000,10.0000,0.0000,1.0120,4.4000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,8.7740,1.1040,4.6000,10.0000,0.0000,1.1040,4.6000,10.0000,0.0000,1.1040,4.6000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,8.8700,1.2000,4.8000,10.0000,-0.0000,1.2000,4.8000,10.0000,0.0000,1.2000,4.8000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,8.9700,1.3000,5.0000,10.0000,0.0000,1.3000,5.0000,10.0000,0.0000,1.3000,5.0000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,9.0740,1.4040,5.2000,10.0000,0.0000,1.4040,5.2000,10.0000,0.0000,1.4040,5.2000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,9.1820,1.5120,5.4000,10.0000,0.0000,1.5120,5.4000,10.0000,0.0000,1.5120,5.4000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,9.2940,1.6240,5.6000,10.0000,0.0000,1.6240,5.6000,10.0000,0.0000,1.6240,5.6000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,9.4100,1.7400,5.8000,10.0000,0.0000,1.7400,5.8000,10.0000,0.0000,1.7400,5.8000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,9.5300,1.8600,6.0000,10.0000,0.0000,1.8600,6.0000,10.0000,0.0000,1.8600,6.0000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,9.6540,1.9840,6.2000,10.0000,-0.0000,1.9840,6.2000,10.0000,0.0000,1.9840,6.2000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,9.7820,2.1120,6.4000,10.0000,-0.0000,2.1120,6.4000,10.0000,0.0000,2.1120,6.4000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,9.9140,2.2440,6.6000,10.0000,0.0000,2.2440,6.6000,10.0000,0.0000,2.2440,6.6000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,10.0500,2.3800,6.8000,10.0000,-0.0000,2.3800,6.8000,10.0000,0.0000,2.3800,6.8000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,10.1900,2.5200,7.0000,10.0000,0.0000,2.5200,7.0000,10.0000,0.0000,2.5200,7.0000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,10.3340,2.6640,7.2000,10.0000,-0.0000,2.6640,7.2000,10.0000,0.0000,2.6640,7.2000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,10.4820,2.8120,7.4000,10.0000,0.0000,2.8120,7.4000,10.0000,0.0000,2.8120,7.4000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,10.6340,2.9640,7.6000,10.0000,0.0000,2.9640,7.6000,10.0000,0.0000,2.9640,7.6000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,10.7900,3.1200,7.8000,10.0000,0.0000,3.1200,7.8000,10.0000,0.0000,3.1200,7.8000,10.0000,0.0000,1.5708},
				{0.0200,18.2500,10.9500,3.2800,8.0000,10.0000,-0.0000,3.2800,8.0000,10.0000,0.0000,3.2800,8.0000,10.0000,-0.0000,1.5708},
				{0.0200,18.2500,11.1140,3.4440,8.2000,10.0000,0.0000,3.4440,8.2000,10.0000,0.0000,3.4440,8.2000,10.0000,0.0000,1.5708},
				{0.0200,18.2497,11.0580,5.4447,100.0339,4591.6966,229084.8314,3.6120,8.4000,10.0000,0.0000,5.4448,100.0425,4592.1233,229106.1654,-1.5797},
				{0.0200,18.2446,10.8861,5.6651,11.0191,-4450.7402,-452121.8436,3.7840,8.6000,10.0000,0.0000,5.5684,6.1792,-4693.1638,-464264.3551,-1.6281},
				{0.0200,18.2265,10.7111,5.9433,13.9113,144.6087,229767.4467,3.9600,8.8000,10.0000,0.0000,5.6421,3.6812,-124.9016,228413.1111,-1.7305},
				{0.0200,18.1830,10.5367,6.3093,18.3001,219.4420,3741.6658,4.1400,9.0000,10.0000,0.0000,5.6488,0.3353,-167.2939,-2119.6189,-1.9170},
				{0.0200,18.0969,10.3749,6.7955,24.3118,300.5838,4057.0904,4.3240,9.2000,10.0000,0.0000,5.7685,5.9845,282.4608,22487.7369,-2.2211},
				{0.0200,17.9652,10.2536,7.3210,26.2727,98.0449,-10126.9476,4.5040,9.0000,-10.0000,0.0000,5.9358,8.3650,119.0238,-8171.8519,-2.5693},
				{0.0200,17.8058,10.1802,7.7588,21.8929,-218.9889,-15851.6873,4.6800,8.8000,-10.0000,0.0000,6.0227,4.3452,-200.9868,-16000.5286,-2.8324},
				{0.0200,17.6382,10.1422,8.0889,16.5046,-269.4188,-2521.4980,4.8520,8.6000,-10.0000,0.0000,6.0363,0.6798,-183.2694,885.8705,-2.9908},
				{0.0200,17.4711,10.1251,8.3460,12.8517,-182.6455,4338.6684,5.0200,8.4000,-10.0000,0.0000,6.1151,3.9429,163.1510,17321.0200,-3.0799},
				{0.0200,17.3072,10.1192,8.5559,10.4979,-117.6885,3247.8480,5.1840,8.2000,-10.0000,0.0000,6.2331,5.9007,97.8906,-3263.0170,-3.1259},
				{0.0200,17.1472,10.1183,8.7320,8.8032,-84.7348,1647.6877,5.3440,8.0000,-10.0000,0.0000,6.3771,7.1966,64.7979,-1654.6361,-3.1420},
				{0.0200,16.9912,10.1180,8.8795,7.3770,-71.3071,671.3857,5.5000,7.8000,-10.0000,0.0000,6.5415,8.2229,51.3133,-674.2296,-3.1335},
				{0.0200,16.8393,10.1147,8.9989,5.9662,-70.5410,38.3005,5.6520,7.6000,-10.0000,0.0000,6.7262,9.2331,50.5098,-40.1773,-3.1009},
				{0.0200,16.6917,10.1045,9.0859,4.3528,-80.6732,-506.6103,5.8000,7.4000,-10.0000,0.0000,6.9351,10.4450,60.5934,504.1794,-3.0399},
				{0.0200,16.5493,10.0835,9.1319,2.2975,-102.7651,-1104.5917,5.9440,7.2000,-10.0000,0.0000,7.1770,12.0969,82.5958,1100.1190,-2.9419},
				{0.0200,16.4144,10.0463,9.1403,0.4244,-93.6532,455.5954,6.0840,7.0000,-10.0000,0.0000,7.4652,14.4099,115.6499,1652.7058,-2.7934},
				{0.0200,16.2920,9.9876,9.2132,3.6452,161.0406,12734.6890,6.2200,6.8000,-10.0000,0.0000,7.8096,17.2200,140.5074,1242.8766,-2.5844},
				{0.0200,16.1897,9.9047,9.3336,6.0153,118.5061,-2126.7229,6.3520,6.6000,-10.0000,0.0000,8.1932,19.1799,97.9948,-2125.6315,-2.3318},
				{0.0200,16.1138,9.8021,9.4472,5.6819,-16.6702,-6758.8168,6.4800,6.4000,-10.0000,0.0000,8.5622,18.4504,-36.4738,-6723.4287,-2.0899},
				{0.0200,16.0632,9.6891,9.5111,3.1942,-124.3887,-5385.9274,6.6040,6.2000,-10.0000,0.0000,8.8737,15.5756,-143.7410,-5363.3597,-1.9019},
				{0.0200,16.0322,9.5733,9.5219,0.5409,-132.6642,-413.7739,6.7240,6.0000,-10.0000,0.0000,9.1244,12.5317,-152.1940,-422.6517,-1.7711},
				{0.0200,16.0144,9.4587,9.5504,1.4270,44.3046,8848.4400,6.8400,5.8000,-10.0000,0.0000,9.3278,10.1694,-118.1169,1703.8578,-1.6836},
				{0.0200,16.0052,9.3471,9.6055,2.7521,66.2561,1097.5759,6.9520,5.6000,-10.0000,0.0000,9.4967,8.4464,-86.1497,1598.3585,-1.6267},
				{0.0200,16.0012,9.2391,9.6786,3.6559,45.1886,-1053.3740,7.0600,5.4000,-10.0000,0.0000,9.6396,7.1436,-65.1407,1050.4493,-1.5918},
				{0.0200,16.0001,9.1351,9.7650,4.3185,33.1306,-602.8991,7.1640,5.2000,-10.0000,0.0000,9.7612,6.0814,-53.1101,601.5311,-1.5742},
				{0.0200,16.0000,9.0667,9.8301,3.2559,-53.1287,-4312.9649,7.2325,5.0000,-10.0000,0.0000,9.8330,3.5926,-124.4423,-3566.6089,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,-162.7946,-5483.2977,7.2325,4.8000,-10.0000,0.0000,9.8330,0.0000,-179.6277,-2759.2702,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,8139.7303,7.2325,4.6000,-10.0000,0.0000,9.8330,0.0000,0.0000,8981.3837,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,4.4000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,4.2000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,4.0000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,3.8000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,3.6000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,3.4000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,3.2000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,3.0000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,2.8000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,2.6000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,2.4000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,2.2000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,2.0000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,1.8000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,1.6000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,1.4000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,1.2000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,1.0000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,0.8000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,0.6000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,0.4000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,0.2000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},
				{0.0200,16.0000,9.0667,9.8301,0.0000,0.0000,0.0000,7.2325,-0.0000,-10.0000,0.0000,9.8330,0.0000,0.0000,0.0000,-1.5708},

	    };

	@Override
	public double[][] getPath() {
	    return points;
	}
}
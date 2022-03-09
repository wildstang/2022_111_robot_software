package frc.paths;

import com.team319.trajectory.Path;

public class Fiver extends Path {
   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading
	private static final double[][] points = {
				{0.0200,11.9393,-7.5939,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,0.0040,0.2000,10.0000,0.0000,-1.7479},
				{0.0200,11.9379,-7.6018,0.0120,0.4000,9.9986,-0.0694,0.0120,0.4000,10.0000,0.0000,0.0120,0.4000,10.0014,0.0694,-1.7479},
				{0.0200,11.9358,-7.6136,0.0240,0.5999,9.9967,-0.0933,0.0240,0.6000,10.0000,0.0000,0.0240,0.6001,10.0033,0.0933,-1.7479},
				{0.0200,11.9330,-7.6294,0.0400,0.7998,9.9938,-0.1483,0.0400,0.8000,10.0000,0.0000,0.0400,0.8002,10.0062,0.1483,-1.7479},
				{0.0200,11.9294,-7.6491,0.0600,0.9996,9.9900,-0.1891,0.0600,1.0000,10.0000,0.0000,0.0600,1.0004,10.0100,0.1891,-1.7479},
				{0.0200,11.9252,-7.6727,0.0840,1.1993,9.9855,-0.2227,0.0840,1.2000,10.0000,0.0000,0.0840,1.2007,10.0145,0.2227,-1.7479},
				{0.0200,11.9203,-7.7002,0.1119,1.3989,9.9806,-0.2475,0.1120,1.4000,10.0000,0.0000,0.1121,1.4011,10.0194,0.2475,-1.7479},
				{0.0200,11.9146,-7.7317,0.1439,1.5984,9.9754,-0.2621,0.1440,1.6000,10.0000,0.0000,0.1441,1.6016,10.0246,0.2621,-1.7479},
				{0.0200,11.9083,-7.7672,0.1799,1.7978,9.9700,-0.2653,0.1800,1.8000,10.0000,0.0000,0.1801,1.8022,10.0300,0.2653,-1.7478},
				{0.0200,11.9012,-7.8066,0.2198,1.9971,9.9649,-0.2562,0.2200,2.0000,10.0000,0.0000,0.2202,2.0029,10.0351,0.2562,-1.7478},
				{0.0200,11.8935,-7.8499,0.2637,2.1963,9.9602,-0.2339,0.2640,2.2000,10.0000,0.0000,0.2643,2.2037,10.0398,0.2339,-1.7477},
				{0.0200,11.8851,-7.8971,0.3117,2.3954,9.9563,-0.1981,0.3120,2.4000,10.0000,0.0000,0.3123,2.4046,10.0437,0.1981,-1.7476},
				{0.0200,11.8759,-7.9483,0.3635,2.5945,9.9533,-0.1485,0.3640,2.6000,10.0000,0.0000,0.3645,2.6055,10.0467,0.1485,-1.7475},
				{0.0200,11.8661,-8.0034,0.4194,2.7935,9.9516,-0.0856,0.4200,2.8000,10.0000,0.0000,0.4206,2.8065,10.0484,0.0855,-1.7474},
				{0.0200,11.8555,-8.0625,0.4793,2.9926,9.9514,-0.0097,0.4800,3.0000,10.0000,0.0000,0.4807,3.0074,10.0486,0.0097,-1.7472},
				{0.0200,11.8443,-8.1255,0.5431,3.1916,9.9530,0.0780,0.5440,3.2000,10.0000,0.0000,0.5449,3.2084,10.0470,-0.0780,-1.7470},
				{0.0200,11.8324,-8.1925,0.6109,3.3908,9.9565,0.1760,0.6120,3.4000,10.0000,0.0000,0.6131,3.4092,10.0435,-0.1760,-1.7469},
				{0.0200,11.8198,-8.2634,0.6827,3.5900,9.9621,0.2824,0.6840,3.6000,10.0000,0.0000,0.6853,3.6100,10.0379,-0.2824,-1.7467},
				{0.0200,11.8065,-8.3382,0.7585,3.7894,9.9700,0.3948,0.7600,3.8000,10.0000,0.0000,0.7615,3.8106,10.0300,-0.3948,-1.7464},
				{0.0200,11.7925,-8.4170,0.8383,3.9890,9.9802,0.5101,0.8400,4.0000,10.0000,0.0000,0.8417,4.0110,10.0198,-0.5101,-1.7462},
				{0.0200,11.7779,-8.4997,0.9221,4.1889,9.9927,0.6248,0.9240,4.2000,10.0000,0.0000,0.9259,4.2111,10.0073,-0.6248,-1.7460},
				{0.0200,11.7625,-8.5863,1.0098,4.3890,10.0074,0.7347,1.0120,4.4000,10.0000,0.0000,1.0142,4.4110,9.9926,-0.7347,-1.7458},
				{0.0200,11.7465,-8.6769,1.1016,4.5895,10.0241,0.8349,1.1040,4.6000,10.0000,0.0000,1.1064,4.6105,9.9759,-0.8349,-1.7456},
				{0.0200,11.7299,-8.7715,1.1974,4.7903,10.0425,0.9199,1.2000,4.8000,10.0000,0.0000,1.2026,4.8097,9.9575,-0.9199,-1.7454},
				{0.0200,11.7125,-8.8699,1.2973,4.9916,10.0622,0.9837,1.3000,5.0000,10.0000,0.0000,1.3027,5.0084,9.9378,-0.9837,-1.7452},
				{0.0200,11.6945,-8.9724,1.4011,5.1932,10.0826,1.0191,1.4040,5.2000,10.0000,0.0000,1.4069,5.2068,9.9174,-1.0191,-1.7451},
				{0.0200,11.6757,-9.0787,1.5090,5.3953,10.1029,1.0185,1.5120,5.4000,10.0000,0.0000,1.5150,5.4047,9.8971,-1.0185,-1.7450},
				{0.0200,11.6563,-9.1890,1.6210,5.5977,10.1224,0.9735,1.6240,5.6000,10.0000,0.0000,1.6270,5.6023,9.8776,-0.9735,-1.7449},
				{0.0200,11.6362,-9.3033,1.7370,5.8005,10.1399,0.8746,1.7400,5.8000,10.0000,0.0000,1.7430,5.7995,9.8601,-0.8746,-1.7449},
				{0.0200,11.6168,-9.4136,1.8491,5.6033,-9.8627,-1000.1307,1.8520,5.6000,-10.0000,0.0000,1.8549,5.5967,-10.1373,-999.8693,-1.7450},
				{0.0200,11.5981,-9.5199,1.9572,5.4057,-9.8815,-0.9386,1.9600,5.4000,-10.0000,0.0000,1.9628,5.3943,-10.1185,0.9386,-1.7451},
				{0.0200,11.5800,-9.6224,2.0613,5.2076,-9.9019,-1.0231,2.0640,5.2000,-10.0000,0.0000,2.0667,5.1924,-10.0981,1.0231,-1.7453},
				{0.0200,11.5627,-9.7208,2.1615,5.0092,-9.9230,-1.0550,2.1640,5.0000,-10.0000,0.0000,2.1665,4.9908,-10.0770,1.0550,-1.7455},
				{0.0200,11.5460,-9.8154,2.2577,4.8103,-9.9439,-1.0438,2.2600,4.8000,-10.0000,0.0000,2.2623,4.7897,-10.0561,1.0438,-1.7457},
				{0.0200,11.5300,-9.9060,2.3499,4.6110,-9.9639,-0.9978,2.3520,4.6000,-10.0000,0.0000,2.3541,4.5890,-10.0361,0.9978,-1.7459},
				{0.0200,11.5146,-9.9926,2.4382,4.4114,-9.9824,-0.9246,2.4400,4.4000,-10.0000,0.0000,2.4418,4.3886,-10.0176,0.9246,-1.7461},
				{0.0200,11.5000,-10.0753,2.5224,4.2114,-9.9990,-0.8313,2.5240,4.2000,-10.0000,0.0000,2.5256,4.1886,-10.0010,0.8313,-1.7463},
				{0.0200,11.4860,-10.1541,2.6026,4.0111,-10.0135,-0.7239,2.6040,4.0000,-10.0000,0.0000,2.6054,3.9889,-9.9865,0.7239,-1.7466},
				{0.0200,11.4727,-10.2289,2.6788,3.8106,-10.0256,-0.6078,2.6800,3.8000,-10.0000,0.0000,2.6812,3.7894,-9.9744,0.6078,-1.7468},
				{0.0200,11.4601,-10.2998,2.7510,3.6099,-10.0354,-0.4879,2.7520,3.6000,-10.0000,0.0000,2.7530,3.5901,-9.9646,0.4879,-1.7470},
				{0.0200,11.4482,-10.3668,2.8192,3.4090,-10.0428,-0.3683,2.8200,3.4000,-10.0000,0.0000,2.8208,3.3910,-9.9572,0.3683,-1.7472},
				{0.0200,11.4369,-10.4298,2.8834,3.2081,-10.0478,-0.2526,2.8840,3.2000,-10.0000,0.0000,2.8846,3.1919,-9.9522,0.2526,-1.7473},
				{0.0200,11.4264,-10.4888,2.9435,3.0071,-10.0507,-0.1436,2.9440,3.0000,-10.0000,0.0000,2.9445,2.9929,-9.9493,0.1436,-1.7475},
				{0.0200,11.4165,-10.5440,2.9996,2.8060,-10.0515,-0.0438,3.0000,2.8000,-10.0000,0.0000,3.0004,2.7940,-9.9485,0.0438,-1.7476},
				{0.0200,11.4074,-10.5952,3.0517,2.6050,-10.0506,0.0449,3.0520,2.6000,-10.0000,0.0000,3.0523,2.5950,-9.9494,-0.0449,-1.7477},
				{0.0200,11.3989,-10.6424,3.0998,2.4041,-10.0482,0.1211,3.1000,2.4000,-10.0000,0.0000,3.1002,2.3959,-9.9518,-0.1211,-1.7478},
				{0.0200,11.3912,-10.6857,3.1439,2.2032,-10.0446,0.1838,3.1440,2.2000,-10.0000,0.0000,3.1441,2.1968,-9.9554,-0.1838,-1.7478},
				{0.0200,11.3841,-10.7251,3.1839,2.0024,-10.0399,0.2325,3.1840,2.0000,-10.0000,0.0000,3.1841,1.9976,-9.9601,-0.2325,-1.7479},
				{0.0200,11.3778,-10.7605,3.2200,1.8017,-10.0346,0.2669,3.2200,1.8000,-10.0000,0.0000,3.2200,1.7983,-9.9654,-0.2669,-1.7479},
				{0.0200,11.3722,-10.7920,3.2520,1.6011,-10.0288,0.2873,3.2520,1.6000,-10.0000,0.0000,3.2520,1.5989,-9.9712,-0.2873,-1.7479},
				{0.0200,11.3672,-10.8196,3.2800,1.4006,-10.0229,0.2941,3.2800,1.4000,-10.0000,0.0000,3.2800,1.3994,-9.9771,-0.2941,-1.7479},
				{0.0200,11.3630,-10.8432,3.3040,1.2003,-10.0172,0.2881,3.3040,1.2000,-10.0000,0.0000,3.3040,1.1997,-9.9828,-0.2881,-1.7479},
				{0.0200,11.3600,-10.8600,3.3210,0.8521,-17.4096,-369.6209,3.3210,1.0000,-10.0000,0.0000,3.3210,0.8520,-17.3863,-370.1714,-1.7479},
				{0.0200,11.3600,-10.8600,3.3210,0.0000,-42.6052,-1259.7813,3.3210,0.8000,-10.0000,0.0000,3.3210,0.0000,-42.5989,-1260.6342,-1.7479},
				{0.0200,11.3600,-10.8600,3.3210,0.0000,0.0000,2130.2609,3.3210,0.6000,-10.0000,0.0000,3.3210,0.0000,0.0000,2129.9469,-1.7479},
				{0.0200,11.3600,-10.8600,3.3210,0.0000,0.0000,0.0000,3.3210,0.4000,-10.0000,0.0000,3.3210,0.0000,0.0000,0.0000,-1.7479},
				{0.0200,11.3600,-10.8600,3.3210,0.0000,0.0000,0.0000,3.3210,0.2000,-10.0000,0.0000,3.3210,0.0000,0.0000,0.0000,-1.7479},
				{0.0200,11.3600,-10.8600,3.3210,0.0000,0.0000,0.0000,3.3210,-0.0000,-10.0000,0.0000,3.3210,0.0000,0.0000,0.0000,-1.7479},

	    };

	@Override
	public double[][] getPath() {
	    return points;
	}
}
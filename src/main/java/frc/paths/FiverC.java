package frc.paths;

import com.team319.trajectory.Path;

public class FiverC extends Path {
   // dt,x,y,left.pos,left.vel,left.acc,left.jerk,center.pos,center.vel,center.acc,center.jerk,right.pos,right.vel,right.acc,right.jerk,heading
	private static final double[][] points = {
				{0.0200,19.0031,-8.9594,0.0032,0.1600,8.0000,0.0000,0.0032,0.1600,8.0000,0.0000,0.0032,0.1600,8.0000,0.0000,0.1965},
				{0.0200,19.0094,-8.9581,0.0096,0.3200,8.0000,-0.0005,0.0096,0.3200,8.0000,0.0000,0.0096,0.3200,8.0000,0.0005,0.1965},
				{0.0200,19.0188,-8.9563,0.0192,0.4800,8.0000,-0.0007,0.0192,0.4800,8.0000,0.0000,0.0192,0.4800,8.0000,0.0007,0.1965},
				{0.0200,19.0314,-8.9538,0.0320,0.6400,8.0000,-0.0011,0.0320,0.6400,8.0000,0.0000,0.0320,0.6400,8.0000,0.0011,0.1965},
				{0.0200,19.0471,-8.9506,0.0480,0.8000,7.9999,-0.0014,0.0480,0.8000,8.0000,0.0000,0.0480,0.8000,8.0001,0.0014,0.1965},
				{0.0200,19.0659,-8.9469,0.0672,0.9600,7.9999,-0.0018,0.0672,0.9600,8.0000,0.0000,0.0672,0.9600,8.0001,0.0018,0.1965},
				{0.0200,19.0879,-8.9425,0.0896,1.1200,7.9998,-0.0021,0.0896,1.1200,8.0000,0.0000,0.0896,1.1200,8.0002,0.0021,0.1965},
				{0.0200,19.1130,-8.9375,0.1152,1.2800,7.9998,-0.0024,0.1152,1.2800,8.0000,0.0000,0.1152,1.2800,8.0002,0.0024,0.1965},
				{0.0200,19.1412,-8.9319,0.1440,1.4400,7.9997,-0.0027,0.1440,1.4400,8.0000,0.0000,0.1440,1.4400,8.0003,0.0027,0.1965},
				{0.0200,19.1726,-8.9256,0.1760,1.6000,7.9997,-0.0030,0.1760,1.6000,8.0000,0.0000,0.1760,1.6000,8.0003,0.0030,0.1965},
				{0.0200,19.2071,-8.9188,0.2112,1.7600,7.9996,-0.0032,0.2112,1.7600,8.0000,0.0000,0.2112,1.7600,8.0004,0.0032,0.1965},
				{0.0200,19.2448,-8.9113,0.2496,1.9200,7.9996,-0.0035,0.2496,1.9200,8.0000,0.0000,0.2496,1.9200,8.0004,0.0035,0.1965},
				{0.0200,19.2856,-8.9031,0.2912,2.0799,7.9995,-0.0037,0.2912,2.0800,8.0000,0.0000,0.2912,2.0801,8.0005,0.0037,0.1965},
				{0.0200,19.3295,-8.8944,0.3360,2.2399,7.9994,-0.0038,0.3360,2.2400,8.0000,0.0000,0.3360,2.2401,8.0006,0.0038,0.1965},
				{0.0200,19.3766,-8.8850,0.3840,2.3999,7.9993,-0.0040,0.3840,2.4000,8.0000,0.0000,0.3840,2.4001,8.0007,0.0040,0.1965},
				{0.0200,19.4268,-8.8750,0.4352,2.5599,7.9992,-0.0041,0.4352,2.5600,8.0000,0.0000,0.4352,2.5601,8.0008,0.0041,0.1965},
				{0.0200,19.4802,-8.8644,0.4896,2.7199,7.9992,-0.0042,0.4896,2.7200,8.0000,0.0000,0.4896,2.7201,8.0008,0.0042,0.1965},
				{0.0200,19.5367,-8.8532,0.5472,2.8799,7.9991,-0.0042,0.5472,2.8800,8.0000,0.0000,0.5472,2.8801,8.0009,0.0042,0.1965},
				{0.0200,19.5963,-8.8413,0.6080,3.0399,7.9990,-0.0042,0.6080,3.0400,8.0000,0.0000,0.6080,3.0401,8.0010,0.0042,0.1965},
				{0.0200,19.6591,-8.8288,0.6720,3.1998,7.9989,-0.0041,0.6720,3.2000,8.0000,0.0000,0.6720,3.2002,8.0011,0.0041,0.1965},
				{0.0200,19.7250,-8.8157,0.7392,3.3598,7.9988,-0.0041,0.7392,3.3600,8.0000,0.0000,0.7392,3.3602,8.0012,0.0041,0.1965},
				{0.0200,19.7940,-8.8019,0.8096,3.5198,7.9987,-0.0039,0.8096,3.5200,8.0000,0.0000,0.8096,3.5202,8.0013,0.0039,0.1966},
				{0.0200,19.8662,-8.7875,0.8832,3.6798,7.9987,-0.0038,0.8832,3.6800,8.0000,0.0000,0.8832,3.6802,8.0013,0.0038,0.1966},
				{0.0200,19.9415,-8.7725,0.9600,3.8397,7.9986,-0.0036,0.9600,3.8400,8.0000,0.0000,0.9600,3.8403,8.0014,0.0036,0.1966},
				{0.0200,20.0200,-8.7569,1.0400,3.9997,7.9985,-0.0033,1.0400,4.0000,8.0000,0.0000,1.0400,4.0003,8.0015,0.0033,0.1966},
				{0.0200,20.1016,-8.7407,1.1231,4.1597,7.9985,-0.0030,1.1232,4.1600,8.0000,0.0000,1.1233,4.1603,8.0015,0.0030,0.1966},
				{0.0200,20.1863,-8.7238,1.2095,4.3196,7.9984,-0.0027,1.2096,4.3200,8.0000,0.0000,1.2097,4.3204,8.0016,0.0027,0.1966},
				{0.0200,20.2742,-8.7063,1.2991,4.4796,7.9984,-0.0023,1.2992,4.4800,8.0000,0.0000,1.2993,4.4804,8.0016,0.0023,0.1966},
				{0.0200,20.3652,-8.6882,1.3919,4.6396,7.9983,-0.0019,1.3920,4.6400,8.0000,0.0000,1.3921,4.6404,8.0017,0.0019,0.1966},
				{0.0200,20.4593,-8.6694,1.4879,4.7995,7.9983,-0.0015,1.4880,4.8000,8.0000,0.0000,1.4881,4.8005,8.0017,0.0015,0.1966},
				{0.0200,20.5566,-8.6500,1.5871,4.9595,7.9983,-0.0010,1.5872,4.9600,8.0000,0.0000,1.5873,4.9605,8.0017,0.0010,0.1966},
				{0.0200,20.6571,-8.6300,1.6895,5.1195,7.9983,-0.0004,1.6896,5.1200,8.0000,0.0000,1.6897,5.1205,8.0017,0.0004,0.1966},
				{0.0200,20.7606,-8.6094,1.7951,5.2794,7.9983,0.0002,1.7952,5.2800,8.0000,0.0000,1.7953,5.2806,8.0017,-0.0002,0.1966},
				{0.0200,20.8673,-8.5881,1.9039,5.4394,7.9983,0.0008,1.9040,5.4400,8.0000,0.0000,1.9041,5.4406,8.0017,-0.0008,0.1967},
				{0.0200,20.9772,-8.5663,2.0159,5.5994,7.9983,0.0014,2.0160,5.6000,8.0000,0.0000,2.0161,5.6006,8.0017,-0.0014,0.1967},
				{0.0200,21.0902,-8.5437,2.1310,5.7593,7.9984,0.0021,2.1312,5.7600,8.0000,0.0000,2.1314,5.7607,8.0016,-0.0021,0.1967},
				{0.0200,21.2063,-8.5206,2.2494,5.9193,7.9984,0.0028,2.2496,5.9200,8.0000,0.0000,2.2498,5.9207,8.0016,-0.0028,0.1967},
				{0.0200,21.3255,-8.4968,2.3710,6.0793,7.9985,0.0035,2.3712,6.0800,8.0000,0.0000,2.3714,6.0807,8.0015,-0.0035,0.1967},
				{0.0200,21.4479,-8.4724,2.4958,6.2392,7.9986,0.0043,2.4960,6.2400,8.0000,0.0000,2.4962,6.2408,8.0014,-0.0043,0.1967},
				{0.0200,21.5735,-8.4474,2.6238,6.3992,7.9987,0.0051,2.6240,6.4000,8.0000,0.0000,2.6242,6.4008,8.0013,-0.0051,0.1967},
				{0.0200,21.7021,-8.4218,2.7550,6.5592,7.9988,0.0059,2.7552,6.5600,8.0000,0.0000,2.7554,6.5608,8.0012,-0.0059,0.1968},
				{0.0200,21.8339,-8.3955,2.8894,6.7192,7.9989,0.0067,2.8896,6.7200,8.0000,0.0000,2.8898,6.7208,8.0011,-0.0067,0.1968},
				{0.0200,21.9689,-8.3686,3.0269,6.8792,7.9991,0.0075,3.0272,6.8800,8.0000,0.0000,3.0275,6.8808,8.0009,-0.0075,0.1968},
				{0.0200,22.1070,-8.3411,3.1677,7.0391,7.9992,0.0083,3.1680,7.0400,8.0000,0.0000,3.1683,7.0409,8.0008,-0.0083,0.1968},
				{0.0200,22.2482,-8.3129,3.3117,7.1991,7.9994,0.0091,3.3120,7.2000,8.0000,0.0000,3.3123,7.2009,8.0006,-0.0091,0.1968},
				{0.0200,22.3925,-8.2841,3.4589,7.3591,7.9996,0.0099,3.4592,7.3600,8.0000,0.0000,3.4595,7.3609,8.0004,-0.0099,0.1968},
				{0.0200,22.5400,-8.2547,3.6093,7.5191,7.9998,0.0107,3.6096,7.5200,8.0000,0.0000,3.6099,7.5209,8.0002,-0.0107,0.1969},
				{0.0200,22.6907,-8.2247,3.7628,7.6791,8.0001,0.0114,3.7632,7.6800,8.0000,0.0000,3.7636,7.6809,7.9999,-0.0114,0.1969},
				{0.0200,22.8444,-8.1940,3.9196,7.8391,8.0003,0.0122,3.9200,7.8400,8.0000,0.0000,3.9204,7.8409,7.9997,-0.0122,0.1969},
				{0.0200,23.0013,-8.1627,4.0796,7.9991,8.0006,0.0129,4.0800,8.0000,8.0000,0.0000,4.0804,8.0009,7.9994,-0.0129,0.1969},
				{0.0200,23.1614,-8.1308,4.2428,8.1592,8.0008,0.0135,4.2432,8.1600,8.0000,0.0000,4.2436,8.1608,7.9992,-0.0135,0.1969},
				{0.0200,23.3246,-8.0982,4.4092,8.3192,8.0011,0.0141,4.4096,8.3200,8.0000,0.0000,4.4100,8.3208,7.9989,-0.0141,0.1969},
				{0.0200,23.4909,-8.0650,4.5788,8.4792,8.0014,0.0147,4.5792,8.4800,8.0000,0.0000,4.5796,8.4808,7.9986,-0.0147,0.1970},
				{0.0200,23.6603,-8.0312,4.7516,8.6392,8.0017,0.0151,4.7520,8.6400,8.0000,0.0000,4.7524,8.6408,7.9983,-0.0151,0.1970},
				{0.0200,23.8329,-7.9968,4.9275,8.7993,8.0020,0.0155,4.9280,8.8000,8.0000,0.0000,4.9285,8.8007,7.9980,-0.0155,0.1970},
				{0.0200,24.0087,-7.9617,5.1067,8.9593,8.0023,0.0158,5.1072,8.9600,8.0000,0.0000,5.1077,8.9607,7.9977,-0.0158,0.1970},
				{0.0200,24.1876,-7.9260,5.2891,9.1194,8.0027,0.0161,5.2896,9.1200,8.0000,0.0000,5.2901,9.1206,7.9973,-0.0161,0.1970},
				{0.0200,24.3696,-7.8897,5.4747,9.2794,8.0030,0.0161,5.4752,9.2800,8.0000,0.0000,5.4757,9.2806,7.9970,-0.0161,0.1970},
				{0.0200,24.5547,-7.8527,5.6635,9.4395,8.0033,0.0161,5.6640,9.4400,8.0000,0.0000,5.6645,9.4405,7.9967,-0.0161,0.1970},
				{0.0200,24.7430,-7.8151,5.8555,9.5996,8.0036,0.0160,5.8560,9.6000,8.0000,0.0000,5.8565,9.6004,7.9964,-0.0160,0.1970},
				{0.0200,24.9344,-7.7769,6.0507,9.7597,8.0039,0.0157,6.0512,9.7600,8.0000,0.0000,6.0517,9.7603,7.9961,-0.0157,0.1971},
				{0.0200,25.1290,-7.7381,6.2491,9.9197,8.0043,0.0152,6.2496,9.9200,8.0000,0.0000,6.2501,9.9203,7.9957,-0.0152,0.1971},
				{0.0200,25.3251,-7.6989,6.4491,9.9998,4.0046,-199.9831,6.4496,10.0000,8.0000,0.0000,6.4501,10.0002,3.9954,-200.0169,0.1971},
				{0.0200,25.5212,-7.6597,6.6491,9.9999,0.0048,-199.9916,6.6496,10.0000,8.0000,0.0000,6.6501,10.0001,-0.0048,-200.0084,0.1971},
				{0.0200,25.7174,-7.6206,6.8491,10.0000,0.0048,0.0010,6.8496,10.0000,8.0000,0.0000,6.8501,10.0000,-0.0048,-0.0010,0.1971},
				{0.0200,25.9135,-7.5814,7.0491,10.0001,0.0048,-0.0003,7.0496,10.0000,8.0000,0.0000,7.0501,9.9999,-0.0048,0.0003,0.1971},
				{0.0200,26.1096,-7.5423,7.2491,10.0002,0.0047,-0.0016,7.2496,10.0000,8.0000,0.0000,7.2501,9.9998,-0.0047,0.0016,0.1971},
				{0.0200,26.3026,-7.5037,7.4459,9.8403,-7.9956,-400.0170,7.4464,9.8400,-8.0000,0.0000,7.4469,9.8397,-8.0044,-399.9830,0.1970},
				{0.0200,26.4925,-7.4658,7.6395,9.6804,-7.9959,-0.0149,7.6400,9.6800,-8.0000,0.0000,7.6405,9.6796,-8.0041,0.0149,0.1970},
				{0.0200,26.6792,-7.4286,7.8299,9.5205,-7.9962,-0.0154,7.8304,9.5200,-8.0000,0.0000,7.8309,9.5195,-8.0038,0.0154,0.1970},
				{0.0200,26.8628,-7.3919,8.0171,9.3605,-7.9965,-0.0158,8.0176,9.3600,-8.0000,0.0000,8.0181,9.3595,-8.0035,0.0158,0.1970},
				{0.0200,27.0432,-7.3559,8.2011,9.2006,-7.9968,-0.0161,8.2016,9.2000,-8.0000,0.0000,8.2021,9.1994,-8.0032,0.0161,0.1970},
				{0.0200,27.2205,-7.3205,8.3819,9.0406,-7.9972,-0.0162,8.3824,9.0400,-8.0000,0.0000,8.3829,9.0394,-8.0028,0.0162,0.1970},
				{0.0200,27.3947,-7.2858,8.5595,8.8807,-7.9975,-0.0161,8.5600,8.8800,-8.0000,0.0000,8.5605,8.8793,-8.0025,0.0161,0.1970},
				{0.0200,27.5657,-7.2516,8.7340,8.7207,-7.9978,-0.0160,8.7344,8.7200,-8.0000,0.0000,8.7348,8.7193,-8.0022,0.0160,0.1970},
				{0.0200,27.7336,-7.2181,8.9052,8.5608,-7.9981,-0.0157,8.9056,8.5600,-8.0000,0.0000,8.9060,8.5592,-8.0019,0.0157,0.1970},
				{0.0200,27.8983,-7.1852,9.0732,8.4008,-7.9984,-0.0154,9.0736,8.4000,-8.0000,0.0000,9.0740,8.3992,-8.0016,0.0154,0.1969},
				{0.0200,28.0600,-7.1530,9.2380,8.2408,-7.9987,-0.0149,9.2384,8.2400,-8.0000,0.0000,9.2388,8.2392,-8.0013,0.0149,0.1969},
				{0.0200,28.2184,-7.1214,9.3996,8.0809,-7.9990,-0.0144,9.4000,8.0800,-8.0000,0.0000,9.4004,8.0791,-8.0010,0.0144,0.1969},
				{0.0200,28.3738,-7.0904,9.5580,7.9209,-7.9993,-0.0138,9.5584,7.9200,-8.0000,0.0000,9.5588,7.9191,-8.0007,0.0138,0.1969},
				{0.0200,28.5260,-7.0600,9.7133,7.7609,-7.9996,-0.0132,9.7136,7.7600,-8.0000,0.0000,9.7139,7.7591,-8.0004,0.0132,0.1969},
				{0.0200,28.6750,-7.0303,9.8653,7.6009,-7.9998,-0.0125,9.8656,7.6000,-8.0000,0.0000,9.8659,7.5991,-8.0002,0.0125,0.1968},
				{0.0200,28.8210,-7.0012,10.0141,7.4409,-8.0000,-0.0118,10.0144,7.4400,-8.0000,0.0000,10.0147,7.4391,-8.0000,0.0118,0.1968},
				{0.0200,28.9638,-6.9727,10.1597,7.2809,-8.0003,-0.0111,10.1600,7.2800,-8.0000,0.0000,10.1603,7.2791,-7.9997,0.0111,0.1968},
				{0.0200,29.1034,-6.9449,10.3021,7.1209,-8.0005,-0.0103,10.3024,7.1200,-8.0000,0.0000,10.3027,7.1191,-7.9995,0.0103,0.1968},
				{0.0200,29.2399,-6.9177,10.4413,6.9609,-8.0007,-0.0095,10.4416,6.9600,-8.0000,0.0000,10.4419,6.9591,-7.9993,0.0095,0.1968},
				{0.0200,29.3733,-6.8911,10.5774,6.8008,-8.0008,-0.0087,10.5776,6.8000,-8.0000,0.0000,10.5778,6.7992,-7.9992,0.0087,0.1968},
				{0.0200,29.5035,-6.8651,10.7102,6.6408,-8.0010,-0.0079,10.7104,6.6400,-8.0000,0.0000,10.7106,6.6392,-7.9990,0.0079,0.1967},
				{0.0200,29.6306,-6.8398,10.8398,6.4808,-8.0011,-0.0071,10.8400,6.4800,-8.0000,0.0000,10.8402,6.4792,-7.9989,0.0071,0.1967},
				{0.0200,29.7546,-6.8151,10.9662,6.3208,-8.0013,-0.0063,10.9664,6.3200,-8.0000,0.0000,10.9666,6.3192,-7.9987,0.0063,0.1967},
				{0.0200,29.8754,-6.7910,11.0894,6.1607,-8.0014,-0.0055,11.0896,6.1600,-8.0000,0.0000,11.0898,6.1593,-7.9986,0.0055,0.1967},
				{0.0200,29.9931,-6.7676,11.2094,6.0007,-8.0015,-0.0047,11.2096,6.0000,-8.0000,0.0000,11.2098,5.9993,-7.9985,0.0047,0.1967},
				{0.0200,30.1077,-6.7447,11.3263,5.8407,-8.0015,-0.0039,11.3264,5.8400,-8.0000,0.0000,11.3265,5.8393,-7.9985,0.0039,0.1967},
				{0.0200,30.2191,-6.7225,11.4399,5.6806,-8.0016,-0.0032,11.4400,5.6800,-8.0000,0.0000,11.4401,5.6794,-7.9984,0.0032,0.1967},
				{0.0200,30.3273,-6.7010,11.5503,5.5206,-8.0017,-0.0024,11.5504,5.5200,-8.0000,0.0000,11.5505,5.5194,-7.9983,0.0024,0.1966},
				{0.0200,30.4325,-6.6800,11.6575,5.3606,-8.0017,-0.0018,11.6576,5.3600,-8.0000,0.0000,11.6577,5.3594,-7.9983,0.0018,0.1966},
				{0.0200,30.5345,-6.6597,11.7615,5.2005,-8.0017,-0.0011,11.7616,5.2000,-8.0000,0.0000,11.7617,5.1995,-7.9983,0.0011,0.1966},
				{0.0200,30.6333,-6.6400,11.8623,5.0405,-8.0017,-0.0005,11.8624,5.0400,-8.0000,0.0000,11.8625,5.0395,-7.9983,0.0005,0.1966},
				{0.0200,30.7290,-6.6210,11.9599,4.8805,-8.0017,0.0001,11.9600,4.8800,-8.0000,0.0000,11.9601,4.8795,-7.9983,-0.0001,0.1966},
				{0.0200,30.8216,-6.6025,12.0543,4.7204,-8.0017,0.0007,12.0544,4.7200,-8.0000,0.0000,12.0545,4.7196,-7.9983,-0.0007,0.1966},
				{0.0200,30.9111,-6.5847,12.1455,4.5604,-8.0017,0.0012,12.1456,4.5600,-8.0000,0.0000,12.1457,4.5596,-7.9983,-0.0012,0.1966},
				{0.0200,30.9974,-6.5675,12.2335,4.4004,-8.0016,0.0017,12.2336,4.4000,-8.0000,0.0000,12.2337,4.3996,-7.9984,-0.0017,0.1966},
				{0.0200,31.0805,-6.5509,12.3184,4.2403,-8.0016,0.0021,12.3184,4.2400,-8.0000,0.0000,12.3184,4.2397,-7.9984,-0.0021,0.1966},
				{0.0200,31.1606,-6.5350,12.4000,4.0803,-8.0016,0.0025,12.4000,4.0800,-8.0000,0.0000,12.4000,4.0797,-7.9984,-0.0025,0.1966},
				{0.0200,31.2375,-6.5197,12.4784,3.9203,-8.0015,0.0029,12.4784,3.9200,-8.0000,0.0000,12.4784,3.9197,-7.9985,-0.0029,0.1966},
				{0.0200,31.3112,-6.5050,12.5536,3.7603,-8.0014,0.0032,12.5536,3.7600,-8.0000,0.0000,12.5536,3.7597,-7.9986,-0.0032,0.1966},
				{0.0200,31.3818,-6.4910,12.6256,3.6002,-8.0014,0.0035,12.6256,3.6000,-8.0000,0.0000,12.6256,3.5998,-7.9986,-0.0035,0.1965},
				{0.0200,31.4493,-6.4775,12.6944,3.4402,-8.0013,0.0037,12.6944,3.4400,-8.0000,0.0000,12.6944,3.4398,-7.9987,-0.0037,0.1965},
				{0.0200,31.5136,-6.4647,12.7600,3.2802,-8.0012,0.0039,12.7600,3.2800,-8.0000,0.0000,12.7600,3.2798,-7.9988,-0.0039,0.1965},
				{0.0200,31.5748,-6.4525,12.8224,3.1202,-8.0011,0.0040,12.8224,3.1200,-8.0000,0.0000,12.8224,3.1198,-7.9989,-0.0040,0.1965},
				{0.0200,31.6329,-6.4410,12.8816,2.9601,-8.0011,0.0041,12.8816,2.9600,-8.0000,0.0000,12.8816,2.9599,-7.9989,-0.0041,0.1965},
				{0.0200,31.6878,-6.4300,12.9376,2.8001,-8.0010,0.0042,12.9376,2.8000,-8.0000,0.0000,12.9376,2.7999,-7.9990,-0.0042,0.1965},
				{0.0200,31.7396,-6.4197,12.9904,2.6401,-8.0009,0.0042,12.9904,2.6400,-8.0000,0.0000,12.9904,2.6399,-7.9991,-0.0042,0.1965},
				{0.0200,31.7882,-6.4100,13.0400,2.4801,-8.0008,0.0042,13.0400,2.4800,-8.0000,0.0000,13.0400,2.4799,-7.9992,-0.0042,0.1965},
				{0.0200,31.8338,-6.4010,13.0864,2.3201,-8.0007,0.0041,13.0864,2.3200,-8.0000,0.0000,13.0864,2.3199,-7.9993,-0.0041,0.1965},
				{0.0200,31.8761,-6.3925,13.1296,2.1601,-8.0006,0.0040,13.1296,2.1600,-8.0000,0.0000,13.1296,2.1599,-7.9994,-0.0040,0.1965},
				{0.0200,31.9154,-6.3847,13.1696,2.0000,-8.0006,0.0039,13.1696,2.0000,-8.0000,0.0000,13.1696,2.0000,-7.9994,-0.0039,0.1965},
				{0.0200,31.9514,-6.3775,13.2064,1.8400,-8.0005,0.0038,13.2064,1.8400,-8.0000,0.0000,13.2064,1.8400,-7.9995,-0.0038,0.1965},
				{0.0200,31.9844,-6.3710,13.2400,1.6800,-8.0004,0.0036,13.2400,1.6800,-8.0000,0.0000,13.2400,1.6800,-7.9996,-0.0036,0.1965},
				{0.0200,32.0142,-6.3650,13.2704,1.5200,-8.0003,0.0034,13.2704,1.5200,-8.0000,0.0000,13.2704,1.5200,-7.9997,-0.0034,0.1965},
				{0.0200,32.0409,-6.3597,13.2976,1.3600,-8.0003,0.0031,13.2976,1.3600,-8.0000,0.0000,13.2976,1.3600,-7.9997,-0.0031,0.1965},
				{0.0200,32.0644,-6.3550,13.3216,1.2000,-8.0002,0.0029,13.3216,1.2000,-8.0000,0.0000,13.3216,1.2000,-7.9998,-0.0029,0.1965},
				{0.0200,32.0848,-6.3510,13.3424,1.0400,-8.0002,0.0026,13.3424,1.0400,-8.0000,0.0000,13.3424,1.0400,-7.9998,-0.0026,0.1965},
				{0.0200,32.1021,-6.3475,13.3600,0.8800,-8.0001,0.0023,13.3600,0.8800,-8.0000,0.0000,13.3600,0.8800,-7.9999,-0.0023,0.1965},
				{0.0200,32.1162,-6.3447,13.3744,0.7200,-8.0001,0.0019,13.3744,0.7200,-8.0000,0.0000,13.3744,0.7200,-7.9999,-0.0019,0.1965},
				{0.0200,32.1272,-6.3425,13.3856,0.5600,-8.0001,0.0016,13.3856,0.5600,-8.0000,0.0000,13.3856,0.5600,-7.9999,-0.0016,0.1965},
				{0.0200,32.1350,-6.3410,13.3936,0.4000,-8.0000,0.0013,13.3936,0.4000,-8.0000,0.0000,13.3936,0.4000,-8.0000,-0.0013,0.1965},
				{0.0200,32.1397,-6.3401,13.3984,0.2400,-8.0000,0.0009,13.3984,0.2400,-8.0000,0.0000,13.3984,0.2400,-8.0000,-0.0009,0.1965},
				{0.0200,32.1400,-6.3400,13.3987,0.0128,-11.3578,-167.8889,13.3987,0.0800,-8.0000,0.0000,13.3987,0.0128,-11.3578,-167.8901,0.1965},
				{0.0200,32.1397,-6.3401,13.3989,0.0128,0.0000,567.8897,13.3984,-0.0800,-8.0000,0.0000,13.3989,0.0128,0.0000,567.8894,0.1965},

	    };

	@Override
	public double[][] getPath() {
	    return points;
	}
}
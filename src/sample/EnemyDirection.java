//package sample;
//
//public enum EnemyDirection {
//    AHEAD(0),LEFT(-Math.PI/2),RIGHT(Math.PI/2);
//    double dRad;
//     EnemyDirection(double dRad) {
//        this.dRad = dRad;
//    }
//
//    public double getdRad() {
//        return dRad;
//    }
//    public void setEnemyDirection (EnemyDirection enemyDirection) {
//        this.dRad = enemyDirection.dRad;
//    }
//    public EnemyDirection getEnemyDirection() {
//         switch ((int)(dRad /Math.PI * 2)) {
//             case  -1:
//                 return LEFT;
//             case 0:
//                 return AHEAD;
//             case 1:
//                 return RIGHT;
//         }
//         return null;
//    }
//}

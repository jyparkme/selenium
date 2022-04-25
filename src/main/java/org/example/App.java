package org.example;

import java.util.Scanner;

public class App {
    public static void main( String[] args ) throws Exception{

        Conn conn = new Conn();
        Scanner sc = new Scanner(System.in);

        escape:
        do {
            System.out.println("시작날짜를 입력해주세요 (YYYY-MM-DD)");
            conn.startDate = sc.nextLine();
            System.out.println("시작날짜: " + conn.startDate);
            System.out.println();
            System.out.println("종료날짜를 입력해주세요 (YYYY-MM-DD)");
            conn.endDate = sc.nextLine();
            System.out.println("종료날짜: " + conn.endDate);
            System.out.println();
            System.out.println("입력하신 날짜가 맞습니까? (Y/N)");
            String check = sc.nextLine();
            if (check.equals("Y")||check.equals("y")) {
                System.out.println("센터를 선택하여 주세요");
                System.out.println("(0.본원 1.여의도 2.강남 3.수원 4.대구 5.부산 6.광주)");
                int center = sc.nextInt();
                conn.login(center);
                break;
            } else {
                System.out.println("다시 입력하시겠습니까? (Y/N)");
                String checkAgain = sc.nextLine();
                if (checkAgain.equals("Y")||checkAgain.equals("y")) {
                    continue escape;
                }else {
                    System.out.println("종료합니다.");
                    try {
                        Thread.sleep(3000);
                    }catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        break;
                    }
                }
            }
        } while(true);
    }
}
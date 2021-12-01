package Builder.bank ;

public  class   DepositTask implements  Runnable {  
     private int delay ;  //�a������Ԋu���o����C���X�^���X�E�t�B�[���h  
     private BankAccount account; // �a�����������ԍ����o����C���X�^���X�E�t�B�[���h 
     private long  amount ;  // �a��������z���o����C���X�^���X�E�t�B�[���h
     private int  times ;  // �a������̉񐔂��o����C���X�^���X�E�t�B�[���h


    // �R���X�g���N�^�i�a�������̌����C���z�C�񐔂�����������j 
     public DepositTask(BankAccount account, long amount, int times, int delay) {  
         this.account = account ; 
         this.amount = amount ; 
         this.times = times ;  
         this.delay = delay ;
     }  


    // �a��������s�����\�b�h 
     public void run( ) {
         try {
             for ( int i = 1; i <= times;  i++ ) {  
                 account.deposit(amount); // 1��������Ƃ�    
                 Thread.sleep(delay);  // �A������a������ł�0.001�b�Ԃ̊Ԋu��u�� 
             }
         } catch (InterruptedException e) {
        	 Thread.currentThread().interrupt();
         }
     }
}
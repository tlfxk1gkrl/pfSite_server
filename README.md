# pf_server

SPRING BOOT PROJECT

Tool : IntelliJ
Database : Mysql
ImageFile : aws/ec2
-------------------------------------------------
Controller
1. 이미지 업로드
 MutipartFile List를 받아와서 처리 하는 과정.
 1) 업로드 위치 경로 설정 (home/ec2-user/src/) +
 2) 이미지가 저장될 폴더 이름 설정 (현재 시간 값)  
 3) List 를 For 문을 이용하여 new File 로 생성 후 저장
   if(!file.exist()) file.mkdirs();
 4) 파일 저장된 폴더 2)의 주소를 데이터 베이스에 저장.

2. 이미지 불러오기
  1) 데이터베이스에 저장된 이미지 폴더 링크 하위 목록들 String 배열로 가져옴.
  2) 반복문을 이용하여 배열 하나하나의 경로 처리
  2-1) 경로로 InputStream을 생성
  2-2) ByteArrayOutputStream 생성 및 byte[]를 imageStream.available()로 최대 크기로 생성
  2-3) 반복문을 활용하여 생성된 byte[]을 InputStream의 read(byte[] b, off, len)으로 최대 크기로 읽어 옴
  2-4) 읽어온 값을 ByteArrayOutputStream의 write(byte[] b, off, len)이용하여 저장
  2-5) byte[] byte = buffer.toByteArray(); 새로운 배열로 저장
  2-6) return new ResponseEntity<byte[]>(byte, HttpStatus.OK); 이미지 배열을 웹으로 리턴 혹은 리스트에 저장
  3) 1개의 경우 byte[]으로 여러개의 경우 List<byte[]>로 리턴
  
InputStream read()
	read() InputStream에서 1byte를 읽고 읽은 값 리턴
	read(byte[] b) InputStream에서 읽은 byte들을 b에 저장 후, 읽은 바이트 수 리턴
	read(byte[] b, int off, int len) InputStream에서 len개 만큼 byte를 읽은 후, b의 off 부터, len개만큼 저장, 읽은 byte 수 리턴
	
ByteArrayOutputStream write()
	write(int b) 주어진 값을 출력소스에 사용
	write(byte[] b) b[]에 저장된 모든 내용을 출력 소스에 사용
	write(byte[] b, int off, int len) b[]에 저장된 내용을 off부터 len개만큼 읽어서 사용

RP = ReverseProxy
S = Server
geral : $(RP)/*.java $S/*.java 
	javac $(RP)/*.java
	javac $S/*.java

r : 
	rm $(RP)/*.class $S/*.class

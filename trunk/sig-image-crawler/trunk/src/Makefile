all=Main
all:$(all)
lib=org/sig/crawler/fill_image/*.java
Main: org/sig/crawler/fill_image/*.java
	javac org/sig/crawler/fill_image/Main.java -cp $(lib)
run: Main
	java org/sig/crawler/fill_image/Main -cp $(lib)
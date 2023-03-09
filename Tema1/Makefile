JFLAGS = -g
JC = javac
JVM= java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		DistanceMain.java \
		TrieMain.java \
		trie/*.java \
		distance/*.java \

MAIN1 = DistanceMain

MAIN2 = TrieMain

default: build

build: $(CLASSES:.java=.class)

run-p1: build
	$(JVM) $(MAIN1)
run-p2: build
	$(JVM) $(MAIN2)
run-best: build
	$(JVM) $(MAIN1)
clean:
	$(RM) *.class trie/*.class distance/*.class ./test.in ./test.out
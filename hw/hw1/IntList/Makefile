#    make	      # Defaults to the first target: default
#    make default    # Same as gmake compile.
#    make compile    # Compiles Java code in this directory.
#    make style      # Runs style checks (only on instructional machines)
#    make check      # Compile if needed and test using IntListTest.
#    make clean      # Remove extraneous or rebuildable files

# Define $(SRCS) to be a list of Java files.
SRCS = IntListTest.java IntList.java

STYLE_SUPPRESS = $(MASTERDIR)/testing-files/hw1-suppress.xml

# The targets following .PHONY aren't actually files; they serve as commands.
.PHONY: compile default check clean style

# By default, compile all sources
default: compile

# To compile all source files, just bring the file YearCheck.class up to date.
compile: IntListTest.class

# Run our style checks. Will only work on instructional machines
style:
	if [ -f hw1-suppress.xml ]; then \
	    style61b -s hw1-suppress.xml $(SRCS); \
	else \
	    style61b -s $(STYLE_SUPPRESS) $(SRCS); \
	fi

# Test the compiled program, after first making sure it is up-to-date.
check: IntListTest.class
	java IntListTest 

# Remove extraneous or reconstructable files.
clean:
	rm -f *.class *~

IntListTest.class: $(SRCS) 
	javac -g $(SRCS)


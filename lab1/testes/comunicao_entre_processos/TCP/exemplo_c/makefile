EXEC1 := server
EXEC2 := client
LDLIBS := -lnsl

SRCS1 := $(addsufix .c, $(EXEC1))
OBJS1 := $(SRCS1:%.c=%.o)

SRCS2 := $(addsufix .c, $(EXEC2))
OBJS2 := $(SRCS2:%.c=%.o)

all: $(EXEC1) $(EXEC2)

clean:
	$(RM) $(EXEC1) $(EXEC2)

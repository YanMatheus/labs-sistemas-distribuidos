/*
 * This program contains a procedure
 * to add 2 numbers. It demonstrates
 * the "default" mode argument passing.
 * In this mode rpcgen can process
 * only one argument.
 */
struct add_arg {
  int a;
  int b;
};

program ADD_PROG {       /* program number */
  version ADD_VER {          /* version number */
    int add (add_arg) = 1;       /* procedure */
  } = 1;
} = 0x20000199;
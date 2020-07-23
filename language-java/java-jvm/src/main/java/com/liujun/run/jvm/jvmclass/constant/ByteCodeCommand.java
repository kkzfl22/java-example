package com.liujun.run.jvm.jvmclass.constant;

/**
 * 字节码指令
 *
 * @author liujun
 * @version 0.0.1
 */
public enum ByteCodeCommand {
  NOP((byte) 0x00, "nop", "什么都不做"),

  ACONST_NULL((byte) 0x01, "aconst_null", "将null推送至栈顶"),

  ICONST_M1((byte) 0x02, "iconst_m1", "将int型-1推送至栈顶"),

  ICONST_0((byte) 0x03, "iconst_0", "将int型0推送至栈顶"),
  ICONST_1((byte) 0x04, "iconst_1", "将int型1推送至栈顶"),
  ICONST_2((byte) 0x05, "iconst_2", "将int型2推送至栈顶"),
  ICONST_3((byte) 0x06, "iconst_3", "将int型3推送至栈顶"),
  ICONST_4((byte) 0x07, "iconst_4", "将int型4推送至栈顶"),
  ICONST_5((byte) 0x08, "iconst_5", "将int型5推送至栈顶"),

  LCONST_0((byte) 0x09, "lconst_0", "将long型0推送至栈顶"),
  LCONST_1((byte) 0x0a, "lconst_1", "将long型1推送至栈顶"),

  FCONST_0((byte) 0x0b, "fconst_0", "将float型0推送至栈顶"),
  FCONST_1((byte) 0x0c, "fconst_1", "将float型1推送至栈顶"),
  FCONST_2((byte) 0x0d, "fconst_2", "将float型2推送至栈顶"),

  DCONST_0((byte) 0x0e, "dconst_0", "将double型0推送至栈顶"),
  DCONST_1((byte) 0x0f, "dconst_1", "将double型1推送至栈顶"),

  BI_PUSH((byte) 0x10, "bipush", "将单字节的常量值(-128~127)推送至栈顶"),
  SI_PUSH((byte) 0x11, "sipush", "将一个短整型的常量值(-32768~32767)推送至栈顶"),

  LDC((byte) 0x12, "ldc", "将int,float或者String型常量值从常量池中推送至栈顶"),
  LDC_W((byte) 0x13, "ldc_w", "将int,float或者String型常量值从常量池中推送至栈顶（宽索引）"),
  LDC2_W((byte) 0x14, "ldc2_w", "将long或者double型常量值从常量池中推送至栈顶(宽索引)"),

  ILOAD((byte) 0x15, "iload", "将指定的int型本地变量推送至栈顶"),
  LLOAD((byte) 0x16, "lload", "将指定的long型本地变量推送至栈顶"),
  FLOAD((byte) 0x17, "fload", "将指定的float型本地变量推送至栈顶"),
  DLOAD((byte) 0x18, "dload", "将指定的double型本地变量推送至栈顶"),
  ALOAD((byte) 0x19, "aload", "将指定的引用类型变量推送至栈顶"),

  IlOAD_0((byte) 0x1a, "iload_0", "将第一个int型本地变量推送至栈顶"),
  ILOAD_1((byte) 0x1b, "iload_1", "将第二个int型本地变量推送至栈顶"),
  ILOAD_2((byte) 0x1c, "iload_2", "将第三个int型本地变量推送至栈顶"),
  ILOAD_3((byte) 0x1d, "iload_3", "将第四个int型本地变量推送至栈顶"),

  LLOAD_0((byte) 0x1e, "lload_0", "将第一个long型本地变量推送至栈顶"),
  LLOAD_1((byte) 0x1f, "lload_1", "将第二个long型本地变量推送至栈顶"),
  LLOAD_2((byte) 0x20, "lload_2", "将第三个long型本地变量推送至栈顶"),
  LLOAD_3((byte) 0x21, "lload_3", "将第四个long型本地变量推送至栈顶"),

  FLOAD_0((byte) 0x22, "fload_0", "将第一个float型本地变量推送至栈顶"),
  FLOAD_1((byte) 0x23, "fload_1", "将第二个float型本地变量推送至栈顶"),
  FLOAD_2((byte) 0x24, "fload_2", "将第三个float型本地变量推送至栈顶"),
  FLOAD_3((byte) 0x25, "float_3", "将第四个float型本地变量推送至栈顶"),

  DLOAD_0((byte) 0x26, "dload_0", "将第一个double型本地变量推送至栈顶"),
  DLOAD_1((byte) 0x27, "dload_1", "将第二个double型本地变量推送至栈顶"),
  DLOAD_2((byte) 0x28, "dload_2", "将第三个double型本地变量推送至栈顶"),
  DLOAD_3((byte) 0x29, "dload_3", "将第三个double型本地变量推送至栈顶"),

  ALOAD_0((byte) 0x2a, "aload_0", "将第一个引用类型本地变量推送至栈顶"),
  ALOAD_1((byte) 0x2b, "aload_1", "将第二个引用类型本地变量推送至栈顶"),
  ALOAD_2((byte) 0x2c, "aload_2", "将第三个引用类型本地变量推送至栈顶"),
  ALOAD_3((byte) 0x2d, "aload_3", "将第四个引用类型本地变量推送至栈顶"),

  IALOAD((byte) 0x2e, "iload", "将int型数组指定索引的值推送至栈顶"),
  LALOAD((byte) 0x2f, "lload", "将long型数组指定索引的值推送至栈顶"),
  FALOAD((byte) 0x30, "faload", "将float型数组指定索引的值推送至栈顶"),
  DALOAD((byte) 0x31, "daload", "将double型数组指定索引的值推送至栈顶"),
  AALOAD((byte) 0x32, "aaload", "将引用型数组指定索引的值推送至栈顶"),
  BALOAD((byte) 0x33, "baload", "将boolean或byte型数组指定索引的值推送至栈顶"),
  CALOAD((byte) 0x34, "caload", "将char型数组指定索引的值推送至至栈顶"),
  SALOAD((byte) 0x35, "saload", "将short型数组指定索引的值推送至栈顶"),

  ISTORE((byte) 0x36, "istore", "将栈顶的int型数值存入指定本地变量"),
  LSTORE((byte) 0x37, "lstore", "将栈顶的long型数值存入指定本地变量"),
  FSTORE((byte) 0x38, "fstore", "将栈顶的float型数值存入指定本地变量"),
  DSTORE((byte) 0x39, "dstore", "将栈顶的double型数值存入指定本地变量"),
  ASTORE((byte) 0x3a, "astore", "将栈顶的引用型数值存入指定本地变量"),

  ISTORE_0((byte) 0x3b, "istore_0", "将栈顶的int型数值存入第一个本地变量"),
  ISTORE_1((byte) 0x3c, "istore_1", "将栈顶的int型数值存储第二个本地变量"),
  ISTORE_2((byte) 0x3d, "istore_2", "将栈顶的int型数值存储第三个本地变量"),
  ISTORE_3((byte) 0x3e, "istore_3", "将栈顶的int型数值存储第四个本地变量"),

  LSTORE_0((byte) 0x3f, "lstore_0", "将栈顶的long型数值存入第一个本地变量"),
  LSTORE_1((byte) 0x40, "lstore_1", "将栈顶的long型数值存入第二个本地变量"),
  LSTORE_2((byte) 0x41, "lstore_2", "将栈顶的long型数值存入第三个本地变量"),
  LSTORE_3((byte) 0x42, "lstore_3", "将栈顶的long型数值存入第四个本地变量"),

  FSTORE_0((byte) 0x43, "fstore_0", "将栈顶的float型数值存入第一个本地变量"),
  FSTORE_1((byte) 0x44, "fstore_1", "将栈顶的float型数值存入第二个本地变量"),
  FSTORE_2((byte) 0x45, "fstore_2", "将栈顶的float型数值存入第三个本地变量"),
  FSTORE_3((byte) 0x46, "fstore_3", "将栈顶的float型数值存入第四个本地变量"),

  DSTOURE_0((byte) 0x47, "dstore_0", "将栈顶的double型数值存入第一个本地变量"),
  DSTOURE_1((byte) 0x48, "dstore_1", "将栈顶的double型数值存入第二个本地变量"),
  DSTOURE_2((byte) 0x49, "dstore_2", "将栈顶的double型数值存入第三个本地变量"),
  DSTOURE_3((byte) 0x4a, "dstore_3", "将栈顶的double型数值存入第四个本地变量"),

  ASTORE_0((byte) 0x4b, "astore_0", "将栈顶的引用型数值存入第一个本地变量"),
  ASTORE_1((byte) 0x4c, "astore_1", "将栈顶的引用型数值存入第二个本地变量"),
  ASTORE_2((byte) 0x4d, "astore_2", "将栈顶的引用型数值存入第三个本地变量"),
  ASTORE_3((byte) 0x4e, "astore_3", "将栈顶的引用型数值存入第四个本地变量"),

  IASTORE((byte) 0x4f, "iastore", "将int型数值存入指定数组的指定索引位置"),
  lASTORE((byte) 0x50, "lastore", "将long型数值存入指定数组的指定索引位置"),
  FASTORE((byte) 0x51, "fastore", "将float型数值存入指定数组的指定索引位置"),
  DASTORE((byte) 0x52, "dastore", "将double型数值存入指定数组的指定索引位置"),
  AASTORE((byte) 0x53, "aastore", "将引用型数值存入指定数组的指定索引位置"),
  BASTORE((byte) 0x54, "bastore", "将boolean或者byte型数值存入指定数组的指定索引位置"),
  CASTORE((byte) 0x55, "castore", "将char型数值存入指定数组的指定索引位置"),
  SASTORE((byte) 0x56, "sastore", "将short型数值存入指定数组的指定索引位置"),

  POP((byte) 0x57, "pop", "将栈顶的数值弹出（数值不能为(long或者double)）"),
  POP_2((byte) 0x58, "pop2", "将栈顶的一个（long或者double）或者两个(非long或double型)弹出"),

  DUP((byte) 0x59, "dup", "复制栈顶数值并将复制值压入栈顶"),
  DUP_X1((byte) 0x5a, "dup_x1", "复制栈顶数值并将两个复制值压入栈顶"),

  DUP_X2((byte) 0x5b, "dup_x2", "复制栈顶数值并将三个（或两个）复制值压入栈顶"),
  DUP2((byte) 0x5c, "dup2", "复制栈顶一个（对于long或double类型)或者两个(对于非long或double类型)数值并将复制值非过程语言栈顶"),
  DUP2_X1((byte) 0x5d, "dup_x1", "dup_x1指令的双倍版本"),
  DUP2_X2((byte) 0x5e, "dup_x2", "dup_x2指令的双倍版本"),

  SWAP((byte) 0x5f, "swap", "将栈最顶端的两个数值互换（数值不能是long或者double类型)"),

  IADD((byte) 0x60, "iadd", "将栈顶两个int型数值相关并将结果压入栈顶"),
  LADD((byte) 0x61, "ladd", "将栈顶两个long型数值相加并将结果压入栈顶"),
  FADD((byte) 0x62, "fadd", "将栈顶两个float型数值相加并将结果压入栈顶"),
  DADD((byte) 0x63, "dadd", "将栈顶两个double型数值相加并将结果压入栈顶"),

  ISUB((byte) 0x64, "isub", "将栈顶两个int型数值相减并将结果压入栈顶"),
  LSUB((byte) 0x65, "lsub", "将栈顶两个long型数值相减并将结果压入栈顶"),
  FSUB((byte) 0x66, "fsub", "将栈顶两个float型数值相减并将结果压入栈顶"),
  DSUB((byte) 0x67, "dsub", "将栈顶两个double型数值相减并将结果压入栈顶"),

  IMUL((byte) 0x68, "imul", "将栈顶两个int型数值相乘并将结果压入栈顶"),
  LMUL((byte) 0x69, "lmul", "将栈顶两个long型数值相乘并将结果压入栈顶"),
  FMUL((byte) 0x6a, "fmul", "将栈顶两个float型数值相乘并将结果压入栈顶"),
  DMUL((byte) 0x6b, "dmul", "将栈顶两个double型数值相乘并将结果压入栈顶"),

  IDIV((byte)0x6c,"idiv","将栈顶两个int型数值相除并将结果压入栈顶"),
  LDIV((byte)0x6d,"ldiv","将栈顶两个long型数值相除并将结果压入栈顶"),
  FDIV((byte)0x6e,"fdiv","将栈顶两个float型数值相除并将结果压入栈顶"),
  DDIV((byte)0x6f,"ddiv","将栈顶两个double型数值相除并将结果压入栈顶"),

  IREM((byte)0x70,"irem","将栈顶两个int型数值进行取模运算将将结果压入栈顶"),
  LREM((byte)0x71,"lrem","将栈顶两个long型数值进行取模运算将将结果压入栈顶"),
  FREM((byte)0x72,"frem","将栈顶两个float型数值进行取模运算将将结果压入栈顶"),
  DREM((byte)0x73,"drem","将栈顶两个double型数值进行取模运算将将结果压入栈顶"),

  INEG((byte)0x74,"ineg","将栈顶int型数值取负并将结果压入栈顶"),
  LNEG((byte)0x75,"lneg","将栈顶long型数值取负并将结果压入栈顶"),
  FNEG((byte)0x76,"fneg","将栈顶float型数值取负并将结果压入栈顶"),
  DNEG((byte)0x77,"dneg","将栈顶double型数值取负并将结果压入栈顶"),

  ISHL((byte)0x78,"ishl","将int型数值左移指定位数并将结果压入栈顶"),
  LSHL((byte)0x79,"lshl","将long型数值左移指定位数并将结果压入栈顶"),

  ISHR((byte)0x7a,"ishr","将int型数值右（带符号）移指定位数并将结果压入栈顶"),
  LSHR((byte)0x7b,"lshr","将long型数值右（带符号）移指定位数并将结果压入栈顶"),

  IUSHR((byte)0x7c,"iushr","将int型数值右（无符号)移指定位数并将结果压入栈顶"),
  LUSHR((byte)0x7d,"lushr","将long型数值右（无符号)移指定位数并将结果压入栈顶"),

  IAND((byte)0x7e,"iand","将栈顶两int型数值进行“按位与”运算并将结果压入栈顶"),
  LAND((byte)0x7f,"land","将栈顶两long型数值进行“按位与”运算并将结果压入栈顶"),

  IOR((byte)0x80,"ior","将栈顶两个int型数值进行“按位或”运算并将结果压入栈顶"),
  LOR((byte)0x81,"lor","将栈顶两个long型数值进行“按位或”运算并将结果压入栈顶"),

  IXOR((byte)0x82,"ixor","将栈顶两个int型数值进行“按位异或”运算并将结果压入栈顶"),
  LXOR((byte)0x83,"lxor","将栈顶两个long型数值进行“按位异或”运算并将结果压入栈顶"),

  IINC((byte)0x84,"iinc","将指定int型变量增加指定值（如i++,i--,i+=2)"),

  I2L((byte)0x85,"i2l","将栈顶int型数值强制转换成long数值并将结果压入栈顶"),
//  ((byte)0x,"",""),
//  ((byte)0x,"",""),

;

  /** 指令 */
  private byte command;

  /** 符号 */
  private String symbol;

  /** 含义 */
  private String msg;

  ByteCodeCommand(byte command, String symbol, String msg) {
    this.command = command;
    this.symbol = symbol;
    this.msg = msg;
  }

  public byte getCommand() {
    return command;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getMsg() {
    return msg;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ByteCodeCommand{");
    sb.append("command=").append(command);
    sb.append(", symbol='").append(symbol).append('\'');
    sb.append(", msg='").append(msg).append('\'');
    sb.append('}');
    return sb.toString();
  }
}

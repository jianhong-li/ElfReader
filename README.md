# ElfReader
ELF 文件解析程序

## 项目内容说明

> 这是一个解析ELF文件的Demo程序,主要是用于学习Linux ELF文件的基本构成, 
> 从自己解析的角度弄明白一个ELF文件的结构是怎样构成的. 这样可以更深刻的理解其布局.
> 作者曾经解析过CLASS文件的基本格式, 这个可以看作C++版本的`class`文件解析.


## 关于文件结构的一些参考:

0. [ELF Header - ch4.eheader](https://refspecs.linuxfoundation.org/elf/gabi4+/ch4.eheader.html)
1. [ELF Header - elfid](https://refspecs.linuxfoundation.org/elf/gabi4+/ch4.eheader.html#elfid)
2. [elf-64-object-file-4-elf-64-object-file-format-version-14-file-header-eidenteimag0](https://documents.pub/document/elf-64-object-file-4-elf-64-object-file-format-version-14-file-header-eidenteimag0.html)
3. [什么是ELF - 博客园(征途)](https://www.cnblogs.com/lwyeric/p/13582022.html)
4. [ELF文件格式解析 - 博客园(征途)](https://www.cnblogs.com/lwyeric/p/13582112.html#elf_header)
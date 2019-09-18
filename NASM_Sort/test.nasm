%include "asm_io.inc"

SECTION .data
msg1: db "Incorrect number of command line arguments",0
msg2: db "1Incorrect argument should be an integer from 2-9",0
msg3: db "2Incorrect argument should be an integer from 2-9",0
numDisks: dd 0
pegs: dd 0,0,0,0,0,0,0,0,0

L1: db "          o|o          ",0
L2: db "         oo|oo         ",0
L3: db "        ooo|ooo        ",0
L4: db "       oooo|oooo       ",0
L5: db "      ooooo|ooooo      ",0
L6: db "     oooooo|oooooo     ",0
L7: db "    ooooooo|ooooooo    ",0
L8: db "   oooooooo|oooooooo   ",0
L9: db "  ooooooooo|ooooooooo  ",0
Base: db "XXXXXXXXXXXXXXXXXXXXXXX",0
test: db "test",0
test2: db "test2",0

SECTION .bss
SIZE: resd 1
LETTER: resb 1

SECTION .text
   global  asm_main

asm_main:
    enter 0,0
    pusha

    mov eax, [ebp+8]                        ;eax is num arguments
    cmp eax, 2          
    je Correct_Number_of_Arguments
    mov eax, msg1
    call print_string
    call print_nl
    jmp asm_main_end

Correct_Number_of_Arguments:
    mov ebx, dword[ebp+12]                   ;ebx is argv[]
    mov ecx, dword[ebx+4]                   ;ecx is numDisks
  
    mov eax,0                                   
    mov al, byte[ecx]
    sub eax, dword'0'                       ;eax is numDisks
   
    call print_int
    call print_nl

    cmp eax, 2                              ;check greater than or equal 2
    jge Greater_than_2
    mov eax, msg2
    call print_string
    call print_nl
    jmp asm_main_end

Greater_than_2:
    cmp eax, 9                              ;check less than or equal to 9
    jbe Correct_Argument
    mov eax, msg3
    call print_string
    call print_nl
    jmp asm_main_end

Correct_Argument:
    call print_int                          ;print numDisks
    call print_nl

    mov [numDisks], eax                     ;store numDisks
    push eax
    mov ecx, pegs               ;eax is peg array
    push ecx
    call rconf                  ;rearanges array
    add esp, 4
    pop ebx                     ;store numDisks in ebx
    mov pegs, ebx               ;stores peg array

    call print_nl

    mov ecx, 1                  ;set ecx 1
    mov edx, 0                  ;set edx 0

    mov eax, dword[pegs]               ;prints first value in pegs 
    call print_int
    call print_nl

Print_Array:
    cmp ebx, ecx                 ;ebx has num of disks
    je Print_Array_End
    mov eax, edx
    add eax, 4
    mov edx, eax
    mov eax, [pegs+edx]
    call print_int
    call print_nl
    inc ecx
    jmp Print_Array

Print_Array_End:

    call print_nl
    mov ecx, pegs
    push ebx                ;push numDisks
    push ecx                ;push pegs

    call Display               ;display pegs

    pop ecx
    pop ebx
   
    mov eax, ebx            ;set eax numDisks
    sub eax, 1              ;set eax numDisks-1
    push eax                ;push numDisks-1
    mov eax, dword[ecx+4]   
    push eax                ;push A+4

    call Sort

    add esp, 8              ;restore stack


asm_main_end:
    popa
    leave                     
    ret

Sort:
    push ebp
    mov ebp, esp

    mov ebx, dword[ebp+8]            ;
    mov ecx, dword[ebp+12]           

    cmp ecx, 1
    je Sort_end
push eax
mov eax, test
call print_string
call print_nl
pop eax
    mov eax, ecx
    sub eax, 1
    push eax
push eax
mov eax, test
call print_string
call print_nl
pop eax
    mov eax, dword[ebx]

    push eax
;    call Sort
    
    add esp, 8

    jmp Sort_end 
    mov edx, 0

    sortLoop:
   ;     mov eax, ecx
  ;      sub eax,1
 ;       cmp edx, eax
        jmp Sort_end

push eax
mov eax, test
call print_string
call print_nl
pop eax
        mov eax, dword[ebx+4]
        cmp ebx, eax
        jg sortLoop_end
push eax
mov eax, test2
call print_string
call print_nl
pop eax
        push dword[eax]
        push dword[ebx]
        pop dword[eax]
        pop dword[ebx]

        inc edx
        jmp sortLoop

    sortLoop_end:
        mov ecx, dword[ebp+12]
        mov ebx, dword[ebp+8]

 ;       mov eax, dword[ecx]

	push ecx
	push ebx

push eax
mov eax, test
call print_string
call print_nl
pop eax

;        call Display

        add esp, 8

    Sort_end:
push eax
mov eax, test
call print_string
call print_nl
pop eax
    pop ebp
    ret

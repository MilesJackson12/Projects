Display:
    push ebp
    mov ebp, esp

   ; jmp disLOOP_end
    mov ecx, dword[ebp+12]	
    mov ebx, dword[ebp+8]    
;    mov eax, dword[ebx]
;    call print_int
;    call print_nl
    
    mov eax, 4
    mul ecx
    push eax
    pop edx

    sub eax, 4
    mov edx, eax

    mov eax, dword[ebx+edx]
   ; mov eax, edx
    call print_int 
    call print_nl

    mov ecx, 0
    jmp Skip_First
    disLOOP:
	mov eax, edx
	sub eax, 4
	mov edx, eax
	mov eax, dword[ebx+edx]
       
    Skip_First:
	 cmp ecx, [ebp+12]
        je disLOOP_end	

       ; mov edx, eax
	cmp eax, 1
        jne P2
        mov eax, L1
        call print_string
        call print_nl
        jmp disLOOP

	P2:
        cmp eax, 2
        jne P3
        mov eax, L2
        call print_string
        call print_nl
        jmp disLOOP

	P3:
        cmp eax, 3
        jne P4
        mov eax, L3
        call print_string
        call print_nl
        jmp disLOOP

	P4:
        cmp eax, 4
        jne P5
        mov eax, L4
        call print_string
        call print_nl
        jmp disLOOP

	P5:
        cmp eax, 5
        jne P6
        mov eax, L5
        call print_string
        call print_nl
        jmp disLOOP

        P6:
	cmp eax, 6
        jne P7
        mov eax, L6
        call print_string
        call print_nl
        jmp disLOOP

	P7:
        cmp eax, 7
        jne P8
        mov eax, L7
        call print_string
        call print_nl
        jmp disLOOP

        P8:
	cmp eax, 8
        jne P9
        mov eax, L8
        call print_string
        call print_nl
        jmp disLOOP

	P9:
        cmp eax, 9
        jne disLOOP_end
        mov eax, L9
        call print_string
        call print_nl
        jmp disLOOP

    disLOOP_end:
    mov eax, Base
    call print_string
    call print_nl
    pop ebp
    ret

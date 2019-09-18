Sort:
    mov ecx, dword[ebp+12]
    mov edx, dword[ebp+8] 
    
    cmp ecx, 1
    je sorthem_end

    mov eax, dword[ecx]                ;push n-1
    sub eax, 1
    push eax

    mov eax, dword[edx+4]               ;push A[+4]
    push eax
    
    call Sort           ; call call sorthem(A+4,n-1)

    add esp, 8            ;restore stack

    mov ebx, 0

    LOOP:
        mov eax, dword[ecx]             
        sub eax, 1                    ;move eax to n-1

        cmp eax, ebx                  ;compare i = n-1
        je loop_end

        mov eax, dword[edx+4]         ;move eax to A+4
        cmp edx, eax                  ;compare A A+4
        jg loop_end

        push dword[eax]
        push dword[edx]
        pop dword[eax]
        pop dword[edx]                 ; Swap Values
        inc ebx
        jmp LOOP

    loop_end:
        push [numDisks]
        push [pegs]
        Call Display
        add esp, 8

    sorthem_end:
        pop ebp
        ret

(A,n)
  if n = 1
    jmp sorthem_end
  call sorthem(A+4,n-1)
  i=0
  loop:
    if i=n-1 jmp loop_end 
    if A[i] > A[i+1] jmp loop_end
    if A[i] < A[i+1] then 
      swap the values
      increment i
      jmp loop
  loop_end:
  call showp(address of the global array, the original number of disks)
sorthem_end:
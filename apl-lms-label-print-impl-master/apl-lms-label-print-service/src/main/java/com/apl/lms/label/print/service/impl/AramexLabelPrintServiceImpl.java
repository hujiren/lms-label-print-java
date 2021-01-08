package com.apl.lms.label.print.service.impl;

import com.apl.lms.label.print.utils.ConvertImageUtil;

import java.io.IOException;

/**
 * @author hjr start
 * @Classname AramexLabelPrintServiceImpl
 * @Date 2021/1/7 17:54
 */
public class AramexLabelPrintServiceImpl {

    public static void main(String[] args) throws IOException {
        String base64Code = "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IC9DSURGb250VHlwZTIKL0Jhc2VGb250IC9BcmlhbEJvbGQKL0NJRFN5c3RlbUluZm8gPDwgL1JlZ2lzdHJ5IChBZG9iZSkgL09yZGVyaW5nIChJZGVudGl0eSkgL1N1cHBsZW1lbnQgMCA+PgovRm9udERlc2NyaXB0b3IgMTMgMCBSCi9DSURUb0dJRE1hcCAvSWRlbnRpdHkKL1cgWzAgWzY2NiA2NDEgNTQzIDc5MCA0OTQgMzQ1IDQ5NCAyNDcgNDk0IDI0NyAyNDcgMjQ3IDU0MyA0OTQgNjQxIDU0MyA0OTQgMjk1IDI5NSA1NDMgNDk0IDU0MyA1NDMgNTQzIF0KXQo+PgplbmRvYmoKMTYgMCBvYmoKPDwgL0xlbmd0aCA1MjUgPj4Kc3RyZWFtCi9DSURJbml0IC9Qcm9jU2V0IGZpbmRyZXNvdXJjZSBiZWdpbgoxMiBkaWN0IGJlZ2luCmJlZ2luY21hcAovQ0lEU3lzdGVtSW5mbyA8PCAvUmVnaXN0cnkgKEFkb2JlKSAvT3JkZXJpbmcgKFVDUykgL1N1cHBsZW1lbnQgMCA+PiBkZWYKL0NNYXBOYW1lIC9BZG9iZS1JZGVudGl0eS1VQ1MgZGVmCi9DTWFwVHlwZSAyIGRlZgoxIGJlZ2luY29kZXNwYWNlcmFuZ2UKPDAwMDA+IDxGRkZGPgplbmRjb2Rlc3BhY2VyYW5nZQoyIGJlZ2luYmZyYW5nZQo8MDAwMD4gPDAwMDA+IDwwMDAwPgo8MDAwMT4gPDAwMTc+IFs8MDA0Mz4gPDAwNkY+IDwwMDZEPiA8MDA2NT4gPDAwNzI+IDwwMDYzPiA8MDA2OT4gPDAwNjE+IDwwMDZDPiA8MDAyMD4gPDAwNDk+IDwwMDZFPiA8MDA3Nj4gPDAwNDg+IDwwMDYyPiA8MDA3OT4gPDAwNzQ+IDwwMDY2PiA8MDA2OD4gPDAwNzM+IDwwMDc1PiA8MDA2ND4gPDAwNzA+IF0KZW5kYmZyYW5nZQplbmRjbWFwCkNNYXBOYW1lIGN1cnJlbnRkaWN0IC9DTWFwIGRlZmluZXJlc291cmNlIHBvcAplbmQKZW5kCgplbmRzdHJlYW0KZW5kb2JqCjYgMCBvYmoKPDwgL1R5cGUgL0ZvbnQKL1N1YnR5cGUgL1R5cGUwCi9CYXNlRm9udCAv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MCA0OTQgNDQ0IDY0MSA0NDQgNTkyIDU0MyA0OTQgNTQzIDE5NyAxOTcgNDQ0IDY0MSA0OTQgNDk0IDQ5NCAyNDcgNDk0IDQ5NCA0OTQgNDk0IDQ5NCA0OTQgNjQxIDY0MSA0OTQgMjQ3IDY0MSA2OTEgNDk0IDQ5NCA2OTEgNTQzIDc0MCA2OTEgNTkyIF0KXQo+PgplbmRvYmoKMjEgMCBvYmoKPDwgL0xlbmd0aCA3ODQgPj4Kc3RyZWFtCi9DSURJbml0IC9Qcm9jU2V0IGZpbmRyZXNvdXJjZSBiZWdpbgoxMiBkaWN0IGJlZ2luCmJlZ2luY21hcAovQ0lEU3lzdGVtSW5mbyA8PCAvUmVnaXN0cnkgKEFkb2JlKSAvT3JkZXJpbmcgKFVDUykgL1N1cHBsZW1lbnQgMCA+PiBkZWYKL0NNYXBOYW1lIC9BZG9iZS1JZGVudGl0eS1VQ1MgZGVmCi9DTWFwVHlwZSAyIGRlZgoxIGJlZ2luY29kZXNwYWNlcmFuZ2UKPDAwMDA+IDxGRkZGPgplbmRjb2Rlc3BhY2VyYW5nZQoyIGJlZ2luYmZyYW5nZQo8MDAwMD4gPDAwMDA+IDwwMDAwPgo8MDAwMT4gPDAwM0M+IFs8MDA0OT4gPDAwNkU+IDwwMDc2PiA8MDA2Rj4gPDAwNjk+IDwwMDYzPiA8MDA2NT4gPDAwMjA+IDwwMDQ0PiA8MDA2MT4gPDAwNzQ+IDwwMDQxPiA8MDA1Nz4gPDAwNDI+IDwwMDJGPiA8MDA0Qz4gPDAwMjM+IDwwMDUzPiA8MDA2OD4gPDAwNzA+IDwwMDcyPiA8MDA0NT4gPDAwNzg+IDwwMDUyPiA8MDA2RD4gPDAwNjQ+IDwwMDczPiA8MDA0Mz4gPDAwNzk+IDwwMDUwPiA8MDA1QT4gPDAwNzU+IDwwMDQ2PiA8MDA2Qz4gPDAwNkE+IDwwMDZCPiA8MDA1NT4gPDAwMzE+IDwwMDMwPiA8MDAzNT4gPDAwMkU+IDwwMDYyPiA8MDAzOT4gPDAwMzc+IDwwMDM0PiA8MDAzMz4gPDAwMzg+IDwwMDc3PiA8MDA0RT4gPDAwNjc+IDwwMDY2PiA8MDA0OD4gPDAwNDc+IDwwMDMyPiA8MDAzNj4gPDAwNEY+IDwwMDU0PiA8MDA0RD4gPDAwNTE+IDwwMDU2PiBdCmVuZGJmcmFuZ2UKZW5kY21hcApDTWFwTmFtZSBjdXJyZW50ZGljdCAvQ01hcCBkZWZpbmVyZXNvdXJjZSBwb3AKZW5kCmVuZAoKZW5kc3RyZWFtCmVuZG9iago3IDAgb2JqCjw8IC9UeXBlIC9Gb250Ci9TdWJ0eXBlIC9UeXBlMAovQmFzZUZvbnQgL0FyaWFsUmVndWxhcgovRW5jb2RpbmcgL0lkZW50aXR5LUgKL0Rlc2NlbmRhbnRGb250cyBbMjAgMCBSXQovVG9Vbmljb2RlIDIxIDAgUj4+CmVuZG9iagoyIDAgb2JqCjw8Ci9UeXBlIC9QYWdlcwovS2lkcyAKWwo1IDAgUgpdCi9Db3VudCAxCi9Qcm9jU2V0IFsvUERGIC9UZXh0IC9JbWFnZUIgL0ltYWdlQ10KPj4KZW5kb2JqCnhyZWYKMCAyMwowMDAwMDAwMDAwIDY1NTM1IGYgCjAwMDAwMDAwMDkgMDAwMDAgbiAKMDAwMDAyMTgzMCAwMDAwMCBuIAowMDAwMDAwMTcxIDAwMDAwIG4gCjAwMDAwMDAyNjYgMDAwMDAgbiAKMDAwMDAwMDM1MiAwMDAwMCBuIAowMDAwMDExNjUwIDAwMDAwIG4gCjAwMDAwMjE2OTIgMDAwMDAgbiAKMDAwMDAwMDMwMyAwMDAwMCBuIAowMDAwMDAwNjY4IDAwMDAwIG4gCjAwMDAwMDUwMTcgMDAwMDAgbiAKMDAwMDAwMDQ3MiAwMDAwMCBuIAowMDAwMDAwNjQ4IDAwMDAwIG4gCjAwMDAwMDUwMzggMDAwMDAgbiAKMDAwMDAwNTI4OCAwMDAwMCBuIAowMDAwMDEwNzY4IDAwMDAwIG4gCjAwMDAwMTEwNzMgMDAwMDAgbiAKMDAwMDAxMDc0NyAwMDAwMCBuIAowMDAwMDExNzg1IDAwMDAwIG4gCjAwMDAwMTIwMzggMDAwMDAgbiAKMDAwMDAyMDQwMCAwMDAwMCBuIAowMDAwMDIwODU2IDAwMDAwIG4gCjAwMDAwMjAzNzkgMDAwMDAgbiAKdHJhaWxlcgo8PAovU2l6ZSAyMwovSW5mbyAxIDAgUgovUm9vdCA4IDAgUgo+PgpzdGFydHhyZWYKMjE5MjgKJSVFT0YK";
        String fileSaveFullPath = "G:/waybill/label/2020-12-21/aramex-label.pdf";
        ConvertImageUtil.base64GeneratorImage(base64Code, fileSaveFullPath);
    }
}
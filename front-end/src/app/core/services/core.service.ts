import {Injectable, OnInit} from "@angular/core";
import {BehaviorSubject, Subject} from "rxjs";

@Injectable()
export class CoreService  {
    private isLoadingSubject = new BehaviorSubject<boolean>(false);
    private progressSubject = new BehaviorSubject<number>(0);
    private activeLoadings = 0;

    isLoading$ = this.isLoadingSubject.asObservable();
    progress$ = this.progressSubject.asObservable();

    private interval: any;

    startLoading(duration: number = 15) {
        this.activeLoadings++;
        if (this.activeLoadings === 1) {
            this.isLoadingSubject.next(true);
            this.progressSubject.next(0);

            const increment = 100 / duration;
            this.interval = setInterval(() => {
                const newProgress = Math.min(this.progressSubject.value + increment, 100);
                this.progressSubject.next(newProgress);

                if (newProgress >= 100) {
                    clearInterval(this.interval);
                }
            }, 1000);
        }
    }

    completeLoading() {
        this.activeLoadings--;
        if (this.activeLoadings <= 0) {
            this.activeLoadings = 0;
            clearInterval(this.interval);
            this.isLoadingSubject.next(false);
            this.progressSubject.next(0);
        }
    }

    forceComplete() {
        this.activeLoadings = 0;
        clearInterval(this.interval);
        this.isLoadingSubject.next(false);
        this.progressSubject.next(0);
    }
}